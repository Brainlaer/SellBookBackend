package com.analitrix.sellbook.service;

import java.util.*;
import java.util.function.Function;

import com.analitrix.sellbook.dto.SortEnum;
import com.analitrix.sellbook.dto.book.*;
import com.analitrix.sellbook.helpers.dto.FilterDto;
import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.helpers.dto.FlattenDto;
import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.entity.Category;
import com.analitrix.sellbook.repository.BookRepository;
import com.analitrix.sellbook.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

	public ResponseEntity<ResponseHttp> create(BookPostDto bookPostDto) {
		Optional<Category> categoryOptional = categoryRepository.findById(bookPostDto.getCategoryId());
		if (categoryOptional.isEmpty())
			return new ResponseEntity<>(new ResponseHttp(204, "Categoria no encontrada"), HttpStatus.NO_CONTENT);
		if (bookRepository.findByIsxn(bookPostDto.getIsxn()).isPresent())
			return new ResponseEntity<>(new ResponseHttp(406, "El libro con el isxn: " + bookPostDto.getIsxn() + ", ya existe."), HttpStatus.CONFLICT);
		Book book = modelMapper.map(bookPostDto, Book.class);
		book.setId(UUID.randomUUID().toString());
		book.setCategory(categoryOptional.get());
		bookRepository.save(book);
		return new ResponseEntity<>(new ResponseHttp(201, "Libro: " + bookPostDto.getTitle() + ", Creado correctamente."), HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseHttp> createAll(List<BookPostDto> booksPostDto) {
		List<Book> books = new ArrayList<>();
		for (BookPostDto bookPostDto:booksPostDto){
			Optional<Category> categoryOptional = categoryRepository.findById(bookPostDto.getCategoryId());
			if (categoryOptional.isEmpty())
				return new ResponseEntity<>(new ResponseHttp(204, "Categoria no encontrada"), HttpStatus.NO_CONTENT);
			if (bookRepository.findByIsxn(bookPostDto.getIsxn()).isPresent())
				return new ResponseEntity<>(new ResponseHttp(406, "El libro con el isxn: " + bookPostDto.getIsxn() + ", ya existe."), HttpStatus.CONFLICT);
			Book book = modelMapper.map(bookPostDto, Book.class);
			book.setId(UUID.randomUUID().toString());
			book.setCategory(categoryOptional.get());
			books.add(book);
		}
		bookRepository.saveAll(books);
		return new ResponseEntity<>(new ResponseHttp(201, "Libros creados correctamente."), HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseHttp> findOne(String id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			BookGetDto bookGetDto = modelMapper.map(optionalBook.get(), BookGetDto.class);
			return new ResponseEntity<>(new ResponseHttp(200, bookGetDto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp(404,"No existe libro con el id: "+id+"."),HttpStatus.NOT_FOUND);
		}
	}

	public Page<Book> findAll(BookRequestDto request) {
		Sort sort = null;
		if(request.getSort().equals(SortEnum.ASC)) {
			sort = Sort.by(Sort.Order.asc(request.getSortableColumn().toString()));
		}else if(request.getSort().equals(SortEnum.DESC)){
			sort = Sort.by(Sort.Order.desc(request.getSortableColumn().toString()));
		}
		Specification<Book> spec = BookSpecifications.filterBy(request.getIsxn(),request.getTitle(), request.getAuthor(), request.getEditorial(), request.getCategory());
		Pageable pageable= PageRequest.of(request.getOffset(), request.getLimit(),sort);
		return bookRepository.findAll(spec, pageable);
	}

	public ResponseEntity<ResponseHttp> update(String id, BookPutDto bookPutDto) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			BookPutDto bookDto = modelMapper.map(book, BookPutDto.class);
			if(bookPutDto.toString().equals(bookDto.toString())){
				return new ResponseEntity<>(new ResponseHttp(305,"No hay cambios para el libro: "+ bookPutDto.getTitle()+"."),HttpStatus.OK);
			}
			if(bookPutDto.getIsxn()!=null){
				book.setIsxn(bookPutDto.getIsxn());
			}if(bookPutDto.getTitle()!=null){
				book.setTitle(bookPutDto.getTitle());
			} if(bookPutDto.getAuthor()!=null){
				book.setAuthor(bookPutDto.getAuthor());
			} if(bookPutDto.getEditorial()!=null){
				book.setEditorial(bookPutDto.getEditorial());
			} if(bookPutDto.getPublicationDate()!=null){
				book.setPublicationDate(bookPutDto.getPublicationDate());
			} if(bookPutDto.getUnits()!=null){
				book.setUnits(bookPutDto.getUnits());
			} if(bookPutDto.getCost()!=null){
				book.setCost(bookPutDto.getCost());
			} if(bookPutDto.getImage()!=null){
				book.setImage(bookPutDto.getImage());
			} if(bookPutDto.getCategoryId()!=null){
				Optional<Category> categoryOptional = categoryRepository.findById(bookPutDto.getCategoryId());
				if (categoryOptional.isEmpty())
					return new ResponseEntity<>(new ResponseHttp(204, "Categoria no encontrada"), HttpStatus.NO_CONTENT);
				book.setCategory(categoryOptional.get());
			}
			book.setAvailability();
			book.modify();
			bookRepository.save(book);
			return new ResponseEntity<>(new ResponseHttp(200,"Libro: "+ bookPutDto.getTitle()+", actualizado con exito."), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp(404,"Libro con el id: "+id+", no existe."), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> delete(String id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			bookRepository.deleteById(id);
			return new ResponseEntity<>(new ResponseHttp(200,"libro: "+book.get().getTitle()+", eliminado con exito"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp(404,"Libro con el id: "+id+", no existe."), HttpStatus.NOT_FOUND);
		}
	}

	private String queryBuilder(BookRequestDto request) {
		StringBuilder query = new StringBuilder("SELECT b FROM Book b ");
		boolean whereAdded = false;

		if (request.getIsxn() != null || request.getTitle() != null || request.getEditorial() != null
				|| request.getAuthor() != null || request.getCategory() != null) {
			query.append("WHERE ");

			if (request.getIsxn() != null) {
				query.append("CAST(b.isxn AS string) LIKE CONCAT('%', :isxn, '%')");
				whereAdded = true;
			}
			if (request.getTitle() != null) {
				if (whereAdded) query.append(" OR ");
				query.append("LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))");
				whereAdded = true;
			}
			if (request.getEditorial() != null) {
				if (whereAdded) query.append(" OR ");
				query.append("LOWER(b.editorial) LIKE LOWER(CONCAT('%', :editorial, '%'))");
				whereAdded = true;
			}
			if (request.getAuthor() != null) {
				if (whereAdded) query.append(" OR ");
				query.append("LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))");
				whereAdded = true;
			}
			if (request.getCategory() != null) {
				if (whereAdded) query.append(" OR ");
				query.append("LOWER(b.category) LIKE LOWER(CONCAT('%', :category, '%'))");
			}
		}

		query.append(" ORDER BY b.modificationDate");
		return query.toString();
	}

}
