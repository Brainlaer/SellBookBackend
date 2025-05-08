package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.model.core.dto.book.ProductPostDto;
import com.analitrix.sellbook.model.core.dto.book.ProductPutDto;
import com.analitrix.sellbook.model.core.Product;
import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.model.core.dto.book.BookRequestDto;
import com.analitrix.sellbook.service.ProductService;
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
	private ProductService productService;

	@PostMapping("")
	public ResponseEntity<ResponseHttp> create(@RequestBody ProductPostDto productPostDto) {
		return productService.create(productPostDto);
	}
//	@PostMapping("/all")
//	public ResponseEntity<ResponseHttp> createAll(@RequestBody List<BookPostDto> booksPostDto) {
//		return bookService.createAll(booksPostDto);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseHttp> findOneById(@PathVariable String id) {
		return productService.findOne(id);
	}

	@GetMapping("")
	public Page<Product> searchBooks(@ParameterObject BookRequestDto requestDto) {
		return productService.findAll(requestDto);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ResponseHttp> update(@PathVariable String id, @RequestBody ProductPutDto bookPutDto) {
		return productService.update(id, bookPutDto);
	}

	@DeleteMapping("/{id}")
		public ResponseEntity<ResponseHttp> delete(@PathVariable String id) {
			return productService.delete(id);
		}
	}
