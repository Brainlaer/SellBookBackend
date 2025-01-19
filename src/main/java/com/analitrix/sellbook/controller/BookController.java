package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.dto.book.BookPostDto;
import com.analitrix.sellbook.dto.book.BookPutDto;
import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.dto.book.BookRequestDto;
import com.analitrix.sellbook.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Book")
@RestController
@RequestMapping("/api/v1/book")
@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("")
	public ResponseEntity<ResponseHttp> create(@RequestBody BookPostDto bookPostDto) {
		return bookService.create(bookPostDto);
	}
//	@PostMapping("/all")
//	public ResponseEntity<ResponseHttp> createAll(@RequestBody List<BookPostDto> booksPostDto) {
//		return bookService.createAll(booksPostDto);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseHttp> findOneById(@PathVariable String id) {
		return bookService.findOneById(id);
	}

	@GetMapping("")
	public Page<Book> searchBooks(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) String editorial,
			@RequestParam(required = false) String category,
			Pageable pageable
	) {
		return bookService.searchBooks(title, author, editorial, category, pageable);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ResponseHttp> update(@PathVariable String id, @RequestBody BookPutDto bookPutDto) {
		return bookService.update(id, bookPutDto);
	}

	@DeleteMapping("/{id}")
		public ResponseEntity<ResponseHttp> delete(@PathVariable String id) {
			return bookService.delete(id);
		}
	}
