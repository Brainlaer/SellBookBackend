package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.dto.book.BookDtoGet;
import com.analitrix.sellbook.dto.book.BookDtoPost;
import com.analitrix.sellbook.dto.book.BookDtoPut;
import com.analitrix.sellbook.dto.ResponseHttp;
import com.analitrix.sellbook.filters.BookPaginationDto;
import com.analitrix.sellbook.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name="Book")
@RestController
@RequestMapping("/api/v1/book")
@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("")
	public ResponseEntity<ResponseHttp> create(@RequestBody BookDtoPost bookDtoPost) {
		return bookService.create(bookDtoPost);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseHttp> findOneById(@PathVariable String id) {
		return bookService.findOneById(id);
	}

	@GetMapping("")
	public ResponseEntity<ResponseHttp> findOrderedByDateDesc(BookPaginationDto filters) {
		return bookService.findOrderedByDateDesc();
	}

	@GetMapping("/recentlyadded")
	public ResponseEntity<ResponseHttp> findRecentlyAdded() {
		return bookService.findRecentlyAdded();
	}

	@GetMapping("/topcategory/{id}")
	public ResponseEntity<ResponseHttp> findTopByCategory(@PathVariable String id) {
		return bookService.findTopByCategory(id);
	}

	@GetMapping("/title&&author/{stringSearch}")
	public ResponseEntity<ResponseHttp> findAllByTitleAndAuthor(@PathVariable String stringSearch) {
		return bookService.findByTitleAndAuthor(stringSearch);
	}

	@GetMapping("/category/{categorySearch}")
	public ResponseEntity<ResponseHttp> findByCategory(@PathVariable String categorySearch) {
		return bookService.findByCategory(categorySearch);
	}

	@GetMapping("/title&&author/{stringSearch}/category/{categorySearch}")
	public ResponseEntity<ResponseHttp> findByTitleAuthorAndCategory(@PathVariable Long categorySearch, @PathVariable String stringSearch) {
		return bookService.findByTitleAuthorAndCategory(categorySearch, stringSearch);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ResponseHttp> update(@PathVariable String id, @RequestBody BookDtoPut bookDtoPut) {
		return bookService.update(id,bookDtoPut);
	}

	@DeleteMapping("/{id}")
		public ResponseEntity<ResponseHttp> delete(@PathVariable String id) {
			return bookService.delete(id);
		}
	}
