package com.analitrix.sellbook.repository;

import java.util.List;

import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{

	Book findByIsxn(Long isxn);
	List<Book> findAllByOrderByModificationDateDesc();
	List<Book> findByTitleContainingIgnoreCase (String string);
	List<Book> findByAuthorContainingIgnoreCase (String string);
	List<Book> findByCategory(Category category);
}
