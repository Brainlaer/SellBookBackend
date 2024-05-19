package com.analitrix.sellbook.controller;

import java.util.List;

import com.analitrix.sellbook.dto.BookDtoId;
import com.analitrix.sellbook.dto.UserDtoId;
import com.analitrix.sellbook.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellbook/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@PostMapping("/create")
	public ResponseEntity<String> createInvoice(@RequestBody UserDtoId userDtoId, List<BookDtoId> bookDtoIdList) {
		return invoiceService.createInvoice(userDtoId, bookDtoIdList);
	}
}
