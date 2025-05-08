package com.analitrix.sellbook.repository;

import java.util.Optional;

import com.analitrix.sellbook.model.core.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

	Optional<Product> findByIsxn(Long isxn);

	Page<Product> findAll(Pageable pageable);

	@Query("SELECT b.id as id, b.title as name FROM Book b")
	Page<Product> findFlattenDto(Pageable pageable);

	@Query("SELECT b FROM Book b WHERE CAST(b.isxn AS string) LIKE CONCAT('%', :filter, '%') order by b.modificationDate")
	Page<Product> findAllByIsxn(@Param("filter") String filter, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%', :filter, '%') order by b.modificationDate")
	Page<Product> findAllByTitle(@Param("filter") String filter, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.author LIKE CONCAT('%', :filter, '%') order by b.modificationDate")
	Page<Product> findAllByAuthor(@Param("filter") String filter, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.category.id = :filter order by b.modificationDate")
	Page<Product> findAllByCategory(@Param("filter") String filter, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.editorial LIKE CONCAT('%', :filter, '%') order by b.modificationDate")
	Page<Product> findAllByEditorial(@Param("filter") String filter, Pageable pageable);





}
