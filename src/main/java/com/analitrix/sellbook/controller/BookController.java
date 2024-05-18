package com.analitrix.sellbook.controller;

import java.util.List;

import com.analitrix.sellbook.dto.BookDto;
import com.analitrix.sellbook.dto.BookDtoPreview;
import com.analitrix.sellbook.service.BookService;
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

@RestController
@RequestMapping("/sellbook/libro")
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

	@GetMapping("/search/title/{string}")
	public ResponseEntity<List<BookDtoPreview>> findByTitle (@PathVariable String string) {
		return bookService.findByTitle(string);
	}

	@GetMapping("/search/author/{string}")
	public ResponseEntity<List<BookDtoPreview>> findByAuthor(@PathVariable String string) {
		return bookService.findByAuthor(string);
	}

	@GetMapping("/search/category/{idCategory}")
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
