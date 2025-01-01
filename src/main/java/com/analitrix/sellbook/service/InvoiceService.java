package com.analitrix.sellbook.service;

import java.util.List;
import java.util.Optional;

import com.analitrix.sellbook.dto.book.BookDtoId;
import com.analitrix.sellbook.dto.InvoiceDtoCreate;
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

	public ResponseEntity<String> createInvoice(InvoiceDtoCreate invoiceDtoCreate) {
		Optional<User> person = userRepository.findById(invoiceDtoCreate.getPerson());

		if (person.isPresent()) {
			User userFound =person.get();
			InvoiceUser invoiceUser = new InvoiceUser();
			invoiceUser.setDocumentNumber(userFound.getDocumentNumber());
			invoiceUser.setFullName(userFound.getName()+" "+ userFound.getSurname());
			invoiceUser.setPhone(userFound.getPhone());
			invoiceUser.setMail(userFound.getMail());
			invoiceUser.setHomeAddress(userFound.getHomeAddress());
			invoiceUserRepository.save(invoiceUser);
			Invoice invoice = new Invoice();
			invoice.setInvoiceUser(invoiceUser);
			invoiceRepository.save(invoice);
			System.out.println("usuario encontrado");
			if (!invoiceDtoCreate.getBookDtoIdList().isEmpty()) {
				List<BookDtoId> bookDtoIdList = invoiceDtoCreate.getBookDtoIdList();
				for (BookDtoId bookDtoId : bookDtoIdList) {
					Optional<Book> book = bookRepository.findById(bookDtoId.getId());
					Book bookFound = book.get();

					if (book.isPresent() && bookFound.isAvailable() == true) {
						InvoiceBook invoiceBook = new InvoiceBook();
						invoiceBook.setIsxn(bookFound.getIsxn());
						invoiceBook.setTitle(bookFound.getTitle());
						invoiceBook.setCost(bookFound.getCost());
						invoiceBook.setInvoice(invoice);
						bookFound.sell();
						bookFound.setAvailability();
						invoice.setTotalCost(invoice.getTotalCost()+invoiceBook.getCost());
						invoiceBookRepository.save(invoiceBook);
					} else {
						return new ResponseEntity<>("No se encontró algunos libros", HttpStatus.NOT_FOUND);
					}
				}
			} else {
				return new ResponseEntity<>("No hay libros en el carrito", HttpStatus.NOT_FOUND);
			}
			Tracking tracking = new Tracking();
			tracking.setStatus("Accepted");
			trackingRepository.save(tracking);
			invoice.setTracking(tracking.getId());
			invoiceRepository.save(invoice);
			return new ResponseEntity<>("Factura Generada", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
		}
	}
}
