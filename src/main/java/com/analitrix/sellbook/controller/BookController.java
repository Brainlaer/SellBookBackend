package com.analitrix.sellbook.controller;

import java.util.List;

import com.analitrix.sellbook.dto.BookDto;
import com.analitrix.sellbook.dto.BookDtoPreview;
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

	@PostMapping("/insert")
	public ResponseEntity<ResponseHttp> insertAsAdmin(@RequestBody BookDto bookDto) {
		return bookService.insertAsAdmin(bookDto);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<BookDto> findById(@PathVariable Long id) {
		return bookService.findById(id);
	}

	@GetMapping("/allBooks")
	public ResponseEntity<List<BookDto>> findAllByDateDesc() {
		return bookService.findAllByDateDescAsAdmin();
	}

	@GetMapping("/recentlyAdded")
	public ResponseEntity<List<BookDtoPreview>> findAllRecentlyAdded() {
		return bookService.findAllRecentlyAdded();
	}

	@GetMapping("/findByTitleAndAuthor/{stringSearch}")
	public ResponseEntity<List<BookDtoPreview>> findAllByTitleAndAuthor(@PathVariable String stringSearch) {
		return bookService.findAllByTitleAndAuthor(stringSearch);
	}

	@GetMapping("/findByTitleAndAuthorAsAdmin/{stringSearch}")
	public ResponseEntity<List<BookDto>> findAllByTitleAndAuthorAsAdmin(@PathVariable String stringSearch) {
		return bookService.findAllByTitleAndAuthorAsAdmin(stringSearch);
	}

	@GetMapping("/findAllById/{id}")
	public ResponseEntity<List<BookDto>> findAllByIdAsAdmin(@PathVariable String stringSearch) {
		return bookService.findAllByIdAsAdmin(stringSearch);
	}

	@GetMapping("/findAllBy/category/{categorySearch}/string/{stringSearch}")
	public ResponseEntity<List<BookDtoPreview>> findAllByCategoryAndTitleAuthor(@PathVariable Long categorySearch, @PathVariable String stringSearch) {
		return bookService.findAllByCategoryAndTitleAuthor(categorySearch, stringSearch);
	}

	@GetMapping("/findAllByCategory/{id}")
	public ResponseEntity<List<BookDtoPreview>> findAllByCategory(@PathVariable Long id) {
		return bookService.findAllByCategory(id);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseHttp> updateAsAdmin(@RequestBody BookDto bookDto) {
		return bookService.updateAsAdmin(bookDto);
	}

	@DeleteMapping("/delete/{id}")
		public ResponseEntity<ResponseHttp> deleteAsAdmin(@PathVariable Long id) {
			return bookService.deleteAsAdmin(id);
		}
	}
