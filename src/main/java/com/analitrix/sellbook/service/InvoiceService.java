package com.analitrix.sellbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.analitrix.sellbook.model.core.dto.InvoiceRequestDto;
import com.analitrix.sellbook.model.core.dto.InvoiceSpecifications;
import com.analitrix.sellbook.model.core.dto.SortEnum;
import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.model.core.dto.InvoiceCreateDto;
import com.analitrix.sellbook.model.security.User;
import com.analitrix.sellbook.model.core.Order;
import com.analitrix.sellbook.model.core.OrderDetail;
import com.analitrix.sellbook.model.core.Product;
import com.analitrix.sellbook.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TrackingRepository trackingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvoiceUserRepository invoiceUserRepository;

	public ResponseEntity<ResponseHttp> findOne(String id){
		Optional<Order> invoiceOptional= orderRepository.findById(id);
		if(invoiceOptional.isPresent()){
			return new ResponseEntity<>(new ResponseHttp(200, invoiceOptional),HttpStatus.OK);
		}else{
			return new ResponseEntity<>(new ResponseHttp(204, "Factura no encontrada"),HttpStatus.CREATED);
		}
	}

	public Page<?> findAll(InvoiceRequestDto request){
        Sort sort = null;
        if(request.getSort().equals(SortEnum.ASC)) {
            sort = Sort.by(Sort.Order.asc(request.getSortableColumn().toString()));
        }else if(request.getSort().equals(SortEnum.DESC)){
            sort = Sort.by(Sort.Order.desc(request.getSortableColumn().toString()));
        }
        Specification<Order> spec = InvoiceSpecifications.filterBy(request.getInvoiceUser());
        Pageable pageable= PageRequest.of(request.getOffset(), request.getLimit(),sort);
        return orderRepository.findAll(spec, pageable);
	}

    public ResponseEntity<ResponseHttp> create(InvoiceCreateDto invoiceCreateDto) {
        Optional<User> userOptional = userRepository.findById(invoiceCreateDto.getUser());

        if (userOptional.isEmpty()) return new ResponseEntity<>(new ResponseHttp(204, "Usuario no encontrado"), HttpStatus.NO_CONTENT);

        User user = userOptional.get();
        InvoiceUser invoiceUser = new InvoiceUser();
        invoiceUser.setDocumentNumber(user.getDocumentNumber());
        invoiceUser.setFullName(user.getName() + " " + user.getSurname());
        invoiceUser.setPhone(user.getPhone());
        invoiceUser.setMail(user.getMail());
        invoiceUser.setHomeAddress(user.getHomeAddress());

		Tracking tracking = new Tracking();
		tracking.setStatus("Accepted");

        Order order = new Order();
        order.setInvoiceUser(invoiceUser);
		order.setTracking(tracking);

        if (invoiceCreateDto.getBooksId().isEmpty()) return new ResponseEntity<>(new ResponseHttp(204, "No hay libros en el carrito"), HttpStatus.NOT_FOUND);

		List<OrderDetail> orderDetails =new ArrayList<>();
		List<Product> products = new ArrayList<>();

        for (String bookId : invoiceCreateDto.getBooksId()) {
            Optional<Product> bookOptional = productRepository.findById(bookId);
            Product product = bookOptional.get();
            if (bookOptional.isEmpty() || !product.isAvailable()) return new ResponseEntity<>(new ResponseHttp(204, "No se encontró algunos libros"), HttpStatus.NOT_FOUND);
			OrderDetail orderDetail = new OrderDetail();
            orderDetail.setIsxn(product.getIsxn());
            orderDetail.setTitle(product.getTitle());
            orderDetail.setCost(product.getCost());
            orderDetail.setOrder(order);
            product.sell();
            product.setAvailability();
            order.setTotalCost(order.getTotalCost() + orderDetail.getCost());
			orderDetails.add(orderDetail);
			products.add(product);
        }
		invoiceUserRepository.save(invoiceUser);
		orderDetailRepository.saveAll(orderDetails);
		productRepository.saveAll(products);
        trackingRepository.save(tracking);
		orderRepository.save(order);
        return new ResponseEntity<>(new ResponseHttp(200,"Factura Generada"), HttpStatus.CREATED);
    }
}
