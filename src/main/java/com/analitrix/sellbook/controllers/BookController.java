package com.analitrix.sellbook.controllers;

import com.analitrix.sellbook.dtos.book.BookCreateDto;
import com.analitrix.sellbook.dtos.book.BookUpdateDto;
import com.analitrix.sellbook.models.Book;
import com.analitrix.sellbook.dtos.common.ResponseHttp;
import com.analitrix.sellbook.dtos.book.BookRequestDto;
import com.analitrix.sellbook.services.BookService;
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
//	public ResponseEntity<ResponseHttp> createMany(@RequestBody List<BookPostDto> booksPostDto) {
//		return bookService.createAll(booksPostDto);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseHttp> findOne(@PathVariable String id) {
		return bookService.findOne(id);
	}

	@GetMapping("")
	public Page<Book> findMany(@ParameterObject BookRequestDto requestDto) {
		return bookService.findMany(requestDto);
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
