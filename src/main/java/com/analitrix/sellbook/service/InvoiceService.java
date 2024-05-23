package com.analitrix.sellbook.service;

import java.util.List;
import java.util.Optional;

import com.analitrix.sellbook.dto.BookDtoId;
import com.analitrix.sellbook.dto.UserDtoId;
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

	public ResponseEntity<String> createInvoice(UserDtoId userDtoId, List<BookDtoId> bookDtoIdList) {
		Optional<User> user = userRepository.findById(userDtoId.getId());

		if (user.isPresent()) {
			Invoice invoice = new Invoice();
			invoice.setUser(userDtoId.getId());
			System.out.println("usuario encontrado");
			if (!bookDtoIdList.isEmpty()) {
				for (BookDtoId bookDtoId : bookDtoIdList) {
					Optional<Book> book = bookRepository.findById(bookDtoId.getIsxn());
					Book bookFound = book.get();

					if (book.isPresent() && bookFound.isAvailable() == true) {
						InvoiceBook invoiceBook = new InvoiceBook();
						invoiceBook.setTitle(bookFound.getTitle());
						invoiceBook.setCost(bookFound.getCost());
						invoiceBook.setInvoice(invoice);
						bookFound.sell();
						bookFound.setAvailability();
						invoice.setTotalCost(invoice.getTotalCost() + invoiceBook.getCost());
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
