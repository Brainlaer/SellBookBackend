package com.analitrix.sellbook.repositories;

import java.util.Optional;

import com.analitrix.sellbook.models.Book;
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
