package com.analitrix.sellbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.analitrix.sellbook.dto.BookDtoGet;
import com.analitrix.sellbook.dto.BookDtoPreview;
import com.analitrix.sellbook.dto.BookDtoPut;
import com.analitrix.sellbook.dto.ResponseHttp;
import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.entity.Category;
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

	public ResponseEntity<ResponseHttp> create(BookDtoGet bookDtoGet) {
		if (!bookRepository.existsById(bookDtoGet.getIsxn())) {
			Book book = modelMapper.map(bookDtoGet, Book.class);
			bookRepository.save(book);
			return new ResponseEntity<>(new ResponseHttp("CREATED","Libro: "+ bookDtoGet.getTitle()+", Guardado correctamente."), HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(new ResponseHttp("BAD_REQUEST","El libro con el id: "+bookDtoGet.getIsxn()+", ya existe."), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<ResponseHttp> findOneById(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			BookDtoGet bookDtoGet = modelMapper.map(optionalBook.get(), BookDtoGet.class);
			return new ResponseEntity<>(new ResponseHttp("OK",bookDtoGet), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp("NOT_FOUND","No existe libro con el id: "+id+"."),HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> findOrderedByDateDesc() {
		List<Book> bookList = bookRepository.findAllByOrderByModificationDateDesc();
		if (!bookList.isEmpty()) {
			List<BookDtoGet> bookDtoGetList = new ArrayList<>();
			for (Book book:bookList){
				BookDtoGet bookDtoGet = modelMapper.map(book, BookDtoGet.class);
				bookDtoGetList.add(bookDtoGet);
			}
			return new ResponseEntity<>(new ResponseHttp("OK",bookDtoGetList), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp("NOT_FOUND","No se encontraron libros."),HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> findRecentlyAdded() {
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
			return new ResponseEntity<>(new ResponseHttp("OK",bookDtoPreviewList), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new ResponseHttp("NOT_FOUND","No se encontraron libros."),HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> findByTitleAndAuthor(String stringSearch) {
		List<Book> bookListTitleAuthor = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(stringSearch, stringSearch);
		List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
		if (!bookListTitleAuthor.isEmpty()) {
			for (Book book : bookListTitleAuthor) {
				BookDtoPreview bookDtoPreview = modelMapper.map(book, BookDtoPreview.class);
				bookDtoPreviewList.add(bookDtoPreview);
			}
			return new ResponseEntity<>(new ResponseHttp("OK",bookDtoPreviewList), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp("NOT_FOUND","No hay libros para la busqueda: "+stringSearch+"."),HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> findByTitleAndAuthorAllData(String stringSearch) {
		List<Book> bookListTitleAuthor = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(stringSearch, stringSearch);
		List<BookDtoGet> bookDtoGetList = new ArrayList<>();
		if (!bookListTitleAuthor.isEmpty()) {
			for (Book book : bookListTitleAuthor) {
				BookDtoGet bookDtoGet = modelMapper.map(book, BookDtoGet.class);
				bookDtoGetList.add(bookDtoGet);
			}
			return new ResponseEntity<>(new ResponseHttp("OK",bookDtoGetList), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp("NOT_FOUND","No hay libros para la busqueda: "+stringSearch+"."),HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> findById(String idSearch){
		List<Book> bookList = bookRepository.findAll();
		List<BookDtoGet> bookDtoGetList = new ArrayList<>();
		for (Book book:bookList){
			String idBook=book.getIsxn().toString();
			if(idBook.contains(idSearch)){
				BookDtoGet bookDtoGet = modelMapper.map(book, BookDtoGet.class);
				bookDtoGetList.add(bookDtoGet);
			}
		}
		if(!bookDtoGetList.isEmpty()){
			return new ResponseEntity<>(new ResponseHttp("OK",bookDtoGetList), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new ResponseHttp("NOT_FOUND","No hay libros para la busqueda: "+idSearch+"."),HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> findByCategory(Long categorySearch) {
		List<Book> bookList = bookRepository.findAllByCategoryId(categorySearch);
		if (!bookList.isEmpty()) {
			List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
			for (Book book : bookList) {
				BookDtoPreview bookDtoPreview = modelMapper.map(book, BookDtoPreview.class);
				bookDtoPreviewList.add(bookDtoPreview);
			}
			return new ResponseEntity<>(new ResponseHttp("OK",bookDtoPreviewList) ,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp("NOT_FOUND","No se encontraron libros en esta categoria."),HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> findByTitleAuthorAndCategory(Long categorySearch, String stringSearch){
		List<Book> bookListCategoryAndTitleOrAuthor = bookRepository.findByCategoryAndTitleAuthor(categorySearch, stringSearch);
		List<BookDtoPreview> bookDtoPreviewList = new ArrayList<>();
		if (!bookListCategoryAndTitleOrAuthor.isEmpty()) {
			for (Book book : bookListCategoryAndTitleOrAuthor) {
				BookDtoPreview bookDtoPreview = modelMapper.map(book, BookDtoPreview.class);
				bookDtoPreviewList.add(bookDtoPreview);
			}
			return new ResponseEntity<>(new ResponseHttp("OK",bookDtoPreviewList), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp("NOT_FOUND","No hay libros para la busqueda: "+stringSearch+", en esta categoria."),HttpStatus.NOT_FOUND);
		}
	}



	public ResponseEntity<ResponseHttp> update(Long id,BookDtoPut bookDtoPut) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			BookDtoPut bookDto = modelMapper.map(book, BookDtoPut.class);
			if(bookDtoPut.toString().equals(bookDto.toString())){
				return new ResponseEntity<>(new ResponseHttp("NOT_MODIFIED","No hay cambios para el libro: "+bookDtoPut.getTitle()+"."),HttpStatus.OK);
			}
			if(bookDtoPut.getTitle()!=null){
				book.setTitle(bookDtoPut.getTitle());
			} if(bookDtoPut.getAuthor()!=null){
				book.setAuthor(bookDtoPut.getAuthor());
			} if(bookDtoPut.getEditorial()!=null){
				book.setEditorial(bookDtoPut.getEditorial());
			} if(bookDtoPut.getPublicationDate()!=null){
				book.setPublicationDate(bookDtoPut.getPublicationDate());
			} if(bookDtoPut.getUnits()!=null){
				book.setUnits(bookDtoPut.getUnits());
			} if(bookDtoPut.getCost()!=null){
				book.setCost(bookDtoPut.getCost());
			} if(bookDtoPut.getImage()!=null){
				book.setImage(bookDtoPut.getImage());
			} if(bookDtoPut.getCategory()!=null){
				book.setCategory(bookDtoPut.getCategory());
			}
			book.setAvailability();
			book.modify();
			bookRepository.save(book);
			return new ResponseEntity<>(new ResponseHttp("OK","Libro: "+bookDtoPut.getTitle()+", actualizado con exito."), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp("NOT_FOUND","Libro con el id: "+id+", no existe."), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseHttp> delete(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			bookRepository.deleteById(id);
			return new ResponseEntity<>(new ResponseHttp("OK","libro: "+book.get().getTitle()+", eliminado con exito"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseHttp("NOT_FOUND","Libro con el id: "+id+", no existe."), HttpStatus.NOT_FOUND);
		}
	}
}
