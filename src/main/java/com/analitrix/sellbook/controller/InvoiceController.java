package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.dto.InvoiceCreateDto;
import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.service.InvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Invoice")
@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return invoiceService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(String id) {
		return invoiceService.findById(id);
	}

	@PostMapping("")
	public ResponseEntity<ResponseHttp> create(@RequestBody InvoiceCreateDto invoiceCreateDto) {
		return invoiceService.create(invoiceCreateDto);
	}
}
