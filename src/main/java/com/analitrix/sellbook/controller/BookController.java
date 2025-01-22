package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.dto.book.BookCreateDto;
import com.analitrix.sellbook.dto.book.BookUpdateDto;
import com.analitrix.sellbook.model.Book;
import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.dto.book.BookRequestDto;
import com.analitrix.sellbook.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	public ResponseEntity<ResponseHttp> create(@RequestBody BookCreateDto bookCreateDto) {
		return bookService.create(bookCreateDto);
	}
//	@PostMapping("/all")
//	public ResponseEntity<ResponseHttp> createAll(@RequestBody List<BookPostDto> booksPostDto) {
//		return bookService.createAll(booksPostDto);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseHttp> findOneById(@PathVariable String id) {
		return bookService.findOne(id);
	}

	@GetMapping("")
	public Page<Book> searchBooks(@ParameterObject BookRequestDto requestDto) {
		return bookService.findAll(requestDto);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ResponseHttp> update(@PathVariable String id, @RequestBody BookUpdateDto bookUpdateDto) {
		return bookService.update(id, bookUpdateDto);
	}

	@DeleteMapping("/{id}")
		public ResponseEntity<ResponseHttp> delete(@PathVariable String id) {
			return bookService.delete(id);
		}
	}
