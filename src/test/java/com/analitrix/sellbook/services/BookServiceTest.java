package com.analitrix.sellbook.services;


import com.analitrix.sellbook.dtos.book.BookCreateDto;
import com.analitrix.sellbook.dtos.book.BookResponseDto;
import com.analitrix.sellbook.dtos.common.ResponseHttp;
import com.analitrix.sellbook.models.Book;
import com.analitrix.sellbook.models.Category;
import com.analitrix.sellbook.repositories.BookRepository;
import com.analitrix.sellbook.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;


public class BookServiceTest {


    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private BookService bookService;

    @Mock
    private ModelMapper modelMapper;

    private BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    final String bookId = "1";
    final Category category = new Category(
            "1",
            "Categoria"
    );

    final Book book = new Book(
            "1",
            123456789L,
            "Titulo",
            2025L,
            true,
            12L,
            "Editorial",
            80000L,
            "Autor",
            "Imagen",
            new Date("10/01/2025"),
            category
    );


    final BookResponseDto bookResponseDto = new BookResponseDto(
            "1",
            123456789L,
            "Titulo",
            2025L,
            12L,
            "Editorial",
            80000L,
            "Autor",
            "Imagen",
            category
    );

    final BookCreateDto bookCreateDto = new BookCreateDto(
            123456789L,
            "Titulo",
            2025L,
            12L,
            "Editorial",
            80000L,
            "Autor",
            "Imagen",
            "1"
    );


    final ResponseEntity<ResponseHttp> responseOk = new ResponseEntity<>(
            new ResponseHttp(
                    200,
                    bookResponseDto
            ), HttpStatus.OK
    );

    final ResponseEntity<ResponseHttp> responseCreated = new ResponseEntity<>(
            new ResponseHttp(
                    201,
                    "Libro: " + bookCreateDto.getTitle() + ", Creado correctamente."
            ), HttpStatus.CREATED
    );

    final ResponseEntity<ResponseHttp> responseExists = new ResponseEntity<>(
            new ResponseHttp(
                    406,
                    "El libro con el isxn: " + bookCreateDto.getIsxn() + ", ya existe."
            ), HttpStatus.CONFLICT
    );

    final ResponseEntity<ResponseHttp> responseCategoryNotFound = new ResponseEntity<>(
            new ResponseHttp(
                    204,
                    "Categoria no encontrada"
            ), HttpStatus.NO_CONTENT
    );

    final ResponseEntity<ResponseHttp> responseError = new ResponseEntity<>(
            new ResponseHttp(
                    404,
                    "No existe libro con el id: "+bookId+"."
            ), HttpStatus.NOT_FOUND
    );


    @Test
    void findOne() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        ResponseEntity<ResponseHttp> response = bookService.findOne(bookId);
        Assertions.assertEquals(response, responseOk);
    }

    @Test
    void findOneFailed(){
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        ResponseEntity<ResponseHttp> response = bookService.findOne(bookId);
        Assertions.assertEquals(response, responseError);
    }

    @Test
    void create(){
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(bookRepository.findByIsxn(book.getIsxn())).thenReturn(Optional.empty());
        ResponseEntity<ResponseHttp> response = bookService.create(bookCreateDto);
        Assertions.assertEquals(response, responseCreated);
    }

    @Test
    void createBookExists(){
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(bookRepository.findByIsxn(book.getIsxn())).thenReturn(Optional.of(book));
        ResponseEntity<ResponseHttp> response = bookService.create(bookCreateDto);
        Assertions.assertEquals(response, responseExists);
    }

    @Test
    void createCategoryNotFound(){
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.empty());
        when(bookRepository.findByIsxn(book.getIsxn())).thenReturn(Optional.of(book));
        ResponseEntity<ResponseHttp> response = bookService.create(bookCreateDto);
        Assertions.assertEquals(response, responseCategoryNotFound);
    }



}