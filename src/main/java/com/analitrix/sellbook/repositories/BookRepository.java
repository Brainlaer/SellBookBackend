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
}
