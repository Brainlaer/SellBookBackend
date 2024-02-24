package com.analitrix.sellbook.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.analitrix.sellbook.dto.LibroIdDto;
import com.analitrix.sellbook.service.FacturaService;

@RestController
@RequestMapping("/sellbook/factura")
public class FacturaController {

	@Autowired
	private FacturaService facturaService;

	@PostMapping("/generar/{usuarioIdDto}")
	public ResponseEntity<String> crearFactura(@PathVariable Long usuarioIdDto, @RequestBody List<LibroIdDto> librosIdDtos) {
		return facturaService.crearFactura(usuarioIdDto, librosIdDtos);
	}
}
