package com.analitrix.sellbook.controller;

import java.util.List;
import java.util.Objects;

import com.analitrix.sellbook.dto.BookDtoGet;
import com.analitrix.sellbook.dto.BookDtoPreview;
import com.analitrix.sellbook.dto.BookDtoPut;
import com.analitrix.sellbook.dto.ResponseHttp;
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

	@PostMapping("")
	public ResponseEntity<ResponseHttp> create(@RequestBody BookDtoGet bookDtoGet) {
		return bookService.create(bookDtoGet);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseHttp> findOneById(@PathVariable Long id) {
		return bookService.findOneById(id);
	}

	@GetMapping("")
	public ResponseEntity<ResponseHttp> findOrderedByDateDesc() {
		return bookService.findOrderedByDateDesc();
	}

	@GetMapping("/recentlyadded")
	public ResponseEntity<ResponseHttp> findRecentlyAdded() {
		return bookService.findRecentlyAdded();
	}

	@GetMapping("/title&&author/{stringSearch}")
	public ResponseEntity<ResponseHttp> findAllByTitleAndAuthor(@PathVariable String stringSearch) {
		return bookService.findByTitleAndAuthor(stringSearch);
	}

	@GetMapping("/category/{categorySearch}")
	public ResponseEntity<ResponseHttp> findByCategory(@PathVariable Long categorySearch) {
		return bookService.findByCategory(categorySearch);
	}

	@GetMapping("/title&&author/{stringSearch}/category/{categorySearch}")
	public ResponseEntity<ResponseHttp> findByTitleAuthorAndCategory(@PathVariable Long categorySearch, @PathVariable String stringSearch) {
		return bookService.findByTitleAuthorAndCategory(categorySearch, stringSearch);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ResponseHttp> update(@PathVariable Long id, @RequestBody BookDtoPut bookDtoPut) {
		return bookService.update(id,bookDtoPut);
	}

	@DeleteMapping("/{id}")
		public ResponseEntity<ResponseHttp> delete(@PathVariable Long id) {
			return bookService.delete(id);
		}
	}
