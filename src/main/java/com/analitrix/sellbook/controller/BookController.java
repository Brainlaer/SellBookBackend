package com.analitrix.sellbook.controller;

import java.util.List;

import com.analitrix.sellbook.dto.BookDto;
import com.analitrix.sellbook.dto.BookDtoPreview;
import com.analitrix.sellbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sellbook/book")
@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/insert")
	public ResponseEntity<String> insertBook(@RequestBody BookDto bookDto) {
		return bookService.insertBook(bookDto);
	}

	@GetMapping("/{isxnBook}")
	public ResponseEntity<BookDto> findById(@PathVariable Long isxnBook) {
		return bookService.findById(isxnBook);
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<BookDto>> findAll() {
		return bookService.findAll();
	}

	@GetMapping("/recentBooks")
	public ResponseEntity<List<BookDtoPreview>> findRecentBooks() {
		return bookService.findRecentBooks();
	}

	@GetMapping("/search/{string}")
	public ResponseEntity<List<BookDtoPreview>> findByAuthorYTitle(@PathVariable String string) {
		return bookService.findByAuthorYTitle(string);
	}

	@GetMapping("/category/{idCategory}")
	public ResponseEntity<List<BookDtoPreview>> findByCategoria(@PathVariable Long idCategory) {
		return bookService.findByCategory(idCategory);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateBook(@RequestBody BookDto bookDto) {
		return bookService.updateBook(bookDto);
	}

	@DeleteMapping("/delete/{isxnBook}")
		public ResponseEntity<String> deleteById(@PathVariable Long isxnBook) {
			return bookService.deleteByIsxn(isxnBook);
		}
	}
