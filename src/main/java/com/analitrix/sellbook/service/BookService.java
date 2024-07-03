package com.analitrix.sellbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.analitrix.sellbook.dto.BookDto;
import com.analitrix.sellbook.dto.BookDtoPreview;
import com.analitrix.sellbook.dto.ResponseHttp;
import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.repository.BookRepository;
import com.analitrix.sellbook.repository.CategoryRepository;
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
	private CategoryRepository categoryRepository;

	ModelMapper modelMapper = new ModelMapper();

	public ResponseEntity<ResponseHttp> insertAsAdmin(BookDto bookDto) {
		Optional<Book> optionalBook = bookRepository.findById(bookDto.getIsxn());
		if (optionalBook.isEmpty()) {
			Book book = modelMapper.map(bookDto, Book.class);
			bookRepository.save(book);
			return new ResponseEntity<>(new ResponseHttp("OK","Libro: "+bookDto.getTitle()+", Guardado correctamente."), HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(new ResponseHttp("ERROR","El libro ya existe."), HttpStatus.CONFLICT);
		}
	}

	public ResponseEntity<BookDto> findById(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			Book book=optionalBook.get();
			BookDto bookDto = modelMapper.map(book, BookDto.class);
			return new ResponseEntity<>(bookDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<BookDto>> findAllByDateDescAsAdmin() {
		List<Book> bookList = bookRepository.findAllByOrderByModificationDateDesc();
		if (!bookList.isEmpty()) {
			List<BookDto> bookDtoList = new ArrayList<>();
			for (Book book:bookList){
				BookDto bookDto = modelMapper.map(book, BookDto.class);
				bookDtoList.add(bookDto);
			}
			return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<List<BookDtoPreview>> findAllRecentlyAdded() {
		List<Book> bookList = bookRepository.findAllByOrderByModificationDateDesc();
		if (!bookList.isEmpty()) {
			List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
			int maxBooks = 10;
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

	public ResponseEntity<List<BookDtoPreview>> findAllByTitleAndAuthor(String stringSearch) {
		List<Book> bookListTitleAuthor = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(stringSearch, stringSearch);
		List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
		if (!bookListTitleAuthor.isEmpty()) {
			for (Book book : bookListTitleAuthor) {
				BookDtoPreview bookDtoPreview = modelMapper.map(book, BookDtoPreview.class);
				bookDtoPreviewList.add(bookDtoPreview);
			}
			return new ResponseEntity<>(bookDtoPreviewList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(bookDtoPreviewList, HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<List<BookDto>> findAllByTitleAndAuthorAsAdmin(String stringSearch) {
		List<Book> bookListTitleAuthor = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(stringSearch, stringSearch);
		List<BookDto> bookDtoList = new ArrayList<>();
		if (!bookListTitleAuthor.isEmpty()) {
			for (Book book : bookListTitleAuthor) {
				BookDto bookDto = modelMapper.map(book, BookDto.class);
				bookDtoList.add(bookDto);
			}
			return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(bookDtoList, HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<List<BookDto>> findAllByIdAsAdmin(String stringSearch){
		List<Book> bookList = bookRepository.findAll();
		List<BookDto> bookDtoList = new ArrayList<>();
		for (Book book:bookList){
			String idBook=book.getIsxn().toString();
			if(idBook.contains(stringSearch)){
				BookDto bookDto = modelMapper.map(book, BookDto.class);
				bookDtoList.add(bookDto);
			}
		}
		if(!bookDtoList.isEmpty()){
			return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<List<BookDtoPreview>> findAllByCategoryAndTitleAuthor(Long categorySearch, String stringSearch){
		List<Book> bookListCategoryAndTitleOrAuthor = bookRepository.findByCategoryAndTitleAuthor(categorySearch, stringSearch);
		List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
		if (!bookListCategoryAndTitleOrAuthor.isEmpty()) {
			for (Book book : bookListCategoryAndTitleOrAuthor) {
				BookDtoPreview bookDtoPreview = modelMapper.map(book, BookDtoPreview.class);
				bookDtoPreviewList.add(bookDtoPreview);
			}
			return new ResponseEntity<>(bookDtoPreviewList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(bookDtoPreviewList, HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<List<BookDtoPreview>> findAllByCategory(Long categorySearch) {
		List<Book> bookList = bookRepository.findAllByCategoryId(categorySearch);
		if (!bookList.isEmpty()) {
			List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
			for (Book book : bookList) {
				BookDtoPreview bookDtoPreview = modelMapper.map(book, BookDtoPreview.class);
				bookDtoPreviewList.add(bookDtoPreview);
			}
			return new ResponseEntity<>(bookDtoPreviewList ,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	public ResponseEntity<ResponseHttp> updateAsAdmin(BookDto bookDto) {
		Optional<Book> optionalBook = bookRepository.findById(bookDto.getIsxn());
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			if(!bookDto.getTitle().isEmpty()){
				book.setTitle(bookDto.getTitle());
			} if(!bookDto.getAuthor().isEmpty()){
				book.setAuthor(bookDto.getAuthor());
			} if(!bookDto.getEditorial().isEmpty()){
				book.setEditorial(bookDto.getEditorial());
			} if(bookDto.getPublicationDate()!=null){
				book.setPublicationDate(bookDto.getPublicationDate());
			} if(bookDto.getUnits()!=null){
				book.setUnits(bookDto.getUnits());
			} if(bookDto.getCost()!=null){
				book.setCost(bookDto.getCost());
			} if(!bookDto.getImage().isEmpty()){
				book.setImage(bookDto.getImage());
			} if(bookDto.getCategory().getId()!=null){
				book.setCategory(bookDto.getCategory());
			}
			bookRepository.save(book);
			return new ResponseEntity<>(new ResponseHttp("OK","Libro actualizado con exito."), HttpStatus.CREATED) ;
		} else {
			return new ResponseEntity<>(new ResponseHttp("ERROR","Parece que el libro no existe."), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> deleteAsAdmin(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			bookRepository.deleteById(id);
			return new ResponseEntity<>(new ResponseHttp("OK","Eliminado con exito"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp("ERROR", "Parece que el libro no existe."), HttpStatus.NOT_FOUND);
		}
	}
}
