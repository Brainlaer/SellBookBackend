package com.analitrix.sellbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.analitrix.sellbook.dto.BookDto;
import com.analitrix.sellbook.dto.BookDtoId;
import com.analitrix.sellbook.dto.BookDtoPreview;
import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.entity.Category;
import com.analitrix.sellbook.repository.BookRepository;
import com.analitrix.sellbook.repository.CategoryRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepositorio categoryRepositorio;

	public ResponseEntity<String> insertBook(BookDto bookDto) {
		Optional<Book> book = bookRepository.findById(bookDto.getIsxn());

		if (book.isEmpty()) {
			ModelMapper modelMapper = new ModelMapper();
			Book bookToSave = modelMapper.map(bookDto, Book.class);

			bookRepository.save(bookToSave);
			return new ResponseEntity<>("¡Guardado con exito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("¡No se pudó guardar!", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public ResponseEntity<BookDto> findById(Long isxnBook) {
		Optional<Book> book = bookRepository.findById(isxnBook);

		if (book.isEmpty()) {
			ModelMapper modelMapper = new ModelMapper();
			BookDto bookToReturn = modelMapper.map(book, BookDto.class);
			return new ResponseEntity<>(bookToReturn, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<BookDto>> findAll() {
		List<Book> bookList = bookRepository.findAllByOrderByDateModifiedDesc();

		if (!bookList.isEmpty()) {
			List<BookDto> bookDtoList = new ArrayList<>();
			ModelMapper modelMapper = new ModelMapper();

			for (Book book:bookList){
				BookDto bookDto = modelMapper.map(book, BookDto.class);
				bookDtoList.add(bookDto);
			}
			return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<List<BookDtoPreview>> findRecentBooks() {
		List<Book> bookList = bookRepository.findAllByOrderByDateModifiedDesc();

		if (!bookList.isEmpty()) {
			List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
			int maxBooks = 4;
			ModelMapper modelMapper = new ModelMapper();

			for (Book book : bookList) {
				BookDtoPreview bookDtoPreview = modelMapper.map(book, BookDtoPreview.class);
				bookDtoPreviewList.add(bookDtoPreview);
				maxBooks--;
				if (maxBooks == 0) {
					break;
				}
			}
			return new ResponseEntity<>(bookDtoPreviewList, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<List<BookDtoPreview>> findByTitle(String string) {
		List<Book> bookList = bookRepository.findByTitleContainingIgnoreCase(string);

		if (!bookList.isEmpty()) {
			List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
			ModelMapper modelMapper = new ModelMapper();

			for (Book book : bookList) {
				BookDtoPreview bookDtoPreview = modelMapper.map(book, BookDtoPreview.class);
				bookDtoPreviewList.add(bookDtoPreview);
			}
			return new ResponseEntity<>(bookDtoPreviewList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<List<BookDtoPreview>> findByAuthor(String string) {
		List<Book> bookList = bookRepository.findByAuthorContainingIgnoreCase(string);

		if (!bookList.isEmpty()) {
			List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
			ModelMapper modelMapper = new ModelMapper();

			for (Book book : bookList) {
				BookDtoPreview bookDtoPreview = modelMapper.map(book, BookDtoPreview.class);
				bookDtoPreviewList.add(bookDtoPreview);
			}
			return new ResponseEntity<>(bookDtoPreviewList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<List<BookDtoPreview>> findByCategory(Long idCategory) {
		Optional<Category> category = categoryRepositorio.findById(idCategory);
		Category categoryFound = category.get();
		List<Book> bookList = bookRepository.findByCategory(categoryFound);

		if (!bookList.isEmpty()) {
			List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
			ModelMapper modelMapper = new ModelMapper();

			for (Book book : bookList) {
				BookDtoPreview bookDtoPreview = modelMapper.map(book, BookDtoPreview.class);
				bookDtoPreviewList.add(bookDtoPreview);
			}
			return new ResponseEntity<>(bookDtoPreviewList ,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<String> updateBook(BookDto bookDto) {
		Optional<Book> book = bookRepository.findById(bookDto.getIsxn());

		if (book.isPresent()) {
			ModelMapper modelMapper = new ModelMapper();
			Book bookFound = book.get();
			bookFound = modelMapper.map(bookDto, Book.class);
			bookRepository.save(bookFound);
			return new ResponseEntity<>("¡Actualizado con exito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("¡No se pudó actualizar!", HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> deleteByIsxn(Long isxnBook) {
		Optional<Book> book = bookRepository.findById(isxnBook);

		if (book.isPresent()) {
			bookRepository.deleteById(isxnBook);
			return new ResponseEntity<>("¡Eliminado con exito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("¡No se pudó eliminar!", HttpStatus.NOT_FOUND);
		}
	}
}
