package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.dto.InvoiceCreateDto;
import com.analitrix.sellbook.dto.InvoiceRequestDto;
import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.service.InvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Invoice")
@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping("")
	public Page<?> findAll(@ParameterObject InvoiceRequestDto invoiceRequestDto) {
		return invoiceService.findAll(invoiceRequestDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(String id) {
		return invoiceService.findOne(id);
	}

	@PostMapping("")
	public ResponseEntity<ResponseHttp> create(@RequestBody InvoiceCreateDto invoiceCreateDto) {
		return invoiceService.create(invoiceCreateDto);
	}
}
