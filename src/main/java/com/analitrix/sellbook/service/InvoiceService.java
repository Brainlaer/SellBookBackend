package com.analitrix.sellbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.dto.InvoiceCreateDto;
import com.analitrix.sellbook.entity.*;
import com.analitrix.sellbook.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceBookRepository invoiceBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TrackingRepository trackingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvoiceUserRepository invoiceUserRepository;

	public ResponseEntity<ResponseHttp> findById(String id){
		Optional<Invoice> invoiceOptional=invoiceRepository.findById(id);
		if(invoiceOptional.isPresent()){
			return new ResponseEntity<>(new ResponseHttp(200, invoiceOptional),HttpStatus.OK);
		}else{
			return new ResponseEntity<>(new ResponseHttp(204, "Factura no encontrada"),HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseHttp> findAll(){
		List<Invoice> invoices=invoiceRepository.findAll();
		if(invoices.stream().count()>0){
			return new ResponseEntity<>(new ResponseHttp(200,invoices) ,HttpStatus.OK);
		}else{
			return new ResponseEntity<>(new ResponseHttp(204, "No hay contenido"),HttpStatus.CREATED);
		}
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

        Invoice invoice = new Invoice();
        invoice.setInvoiceUser(invoiceUser);
		invoice.setTracking(tracking);

        if (invoiceCreateDto.getBooksId().isEmpty()) return new ResponseEntity<>(new ResponseHttp(204, "No hay libros en el carrito"), HttpStatus.NOT_FOUND);

		List<InvoiceBook> invoiceBooks=new ArrayList<>();
		List<Book> books = new ArrayList<>();

        for (String bookId : invoiceCreateDto.getBooksId()) {
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            Book book = bookOptional.get();
            if (bookOptional.isEmpty() || !book.isAvailable()) return new ResponseEntity<>(new ResponseHttp(204, "No se encontró algunos libros"), HttpStatus.NOT_FOUND);
			InvoiceBook invoiceBook = new InvoiceBook();
            invoiceBook.setIsxn(book.getIsxn());
            invoiceBook.setTitle(book.getTitle());
            invoiceBook.setCost(book.getCost());
            invoiceBook.setInvoice(invoice);
            book.sell();
            book.setAvailability();
            invoice.setTotalCost(invoice.getTotalCost() + invoiceBook.getCost());
			invoiceBooks.add(invoiceBook);
			books.add(book);
        }
		invoiceUserRepository.save(invoiceUser);
		invoiceBookRepository.saveAll(invoiceBooks);
		bookRepository.saveAll(books);
        trackingRepository.save(tracking);
		invoiceRepository.save(invoice);
        return new ResponseEntity<>(new ResponseHttp(200,"Factura Generada"), HttpStatus.CREATED);
    }
}
