package com.analitrix.sellbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.analitrix.sellbook.dto.LibroDto;
import com.analitrix.sellbook.entity.Libro;
import com.analitrix.sellbook.service.LibroService;

@RestController
@RequestMapping("/sellbook/libro")
@CrossOrigin(origins = "*")
public class LibroController {

	@Autowired
	private LibroService libroService;

	@PostMapping("/insertar")
	public ResponseEntity<String> insertarLibro(@RequestBody Libro libro) {
		return libroService.insertLibro(libro);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Libro> findById(@PathVariable("id") Long id) {
		return libroService.findById(id);
	}

	@GetMapping("/librosRecientes")
	public ResponseEntity<List<LibroDto>> findAllByOrderByFechaModificacionDesc() {
		return libroService.findAllByOrderByFechaModificacionDesc();
	}

	@GetMapping("/buscar/titulo/{cadena}")
	public ResponseEntity<List<LibroDto>> findByTituloContainingIgnoreCase(@PathVariable String cadena) {
		return libroService.findByTituloContainingIgnoreCase(cadena);
	}

	@GetMapping("/buscar/autor/{cadena}")
	public ResponseEntity<List<LibroDto>> findByAutorContainingIgnoreCase(@PathVariable String cadena) {
		return libroService.findByAutorContainingIgnoreCase(cadena);
	}

	@GetMapping("/categoria")
	public ResponseEntity<List<LibroDto>> findByCategoria(@PathVariable Long categoria) {
		return libroService.findByCategoria(categoria);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<String> updateLibro(@RequestBody Libro libro) {
		return libroService.updateLibro(libro);
	}

	@DeleteMapping("/eliminar/{id}")
		public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
			return libroService.deleteById(id);
		}
	}
