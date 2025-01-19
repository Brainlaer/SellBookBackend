package com.analitrix.sellbook.repository;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import com.analitrix.sellbook.dto.book.BookRequestDto;
import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.entity.Category;
import com.analitrix.sellbook.entity.User;
import com.analitrix.sellbook.helpers.dto.FilterDto;
import com.analitrix.sellbook.helpers.dto.FlattenDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {

	Optional<Book> findByIsxn(Long isxn);

	Page<Book> findAll(Pageable pageable);

	@Query("SELECT b.id as id, b.title as name FROM Book b")
	Page<Book> findFlattenDto(Pageable pageable);

	@Query("SELECT b FROM Book b WHERE CAST(b.isxn AS string) LIKE CONCAT('%', :filter, '%') order by b.modificationDate")
	Page<Book> findAllByIsxn(@Param("filter") String filter, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%', :filter, '%') order by b.modificationDate")
	Page<Book> findAllByTitle(@Param("filter") String filter, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.author LIKE CONCAT('%', :filter, '%') order by b.modificationDate")
	Page<Book> findAllByAuthor(@Param("filter") String filter, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.category.id = :filter order by b.modificationDate")
	Page<Book> findAllByCategory(@Param("filter") String filter, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.editorial LIKE CONCAT('%', :filter, '%') order by b.modificationDate")
	Page<Book> findAllByEditorial(@Param("filter") String filter, Pageable pageable);





}
