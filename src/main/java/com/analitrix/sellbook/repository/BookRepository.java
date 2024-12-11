package com.analitrix.sellbook.repository;

import java.security.SecureRandom;
import java.util.List;

import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findTop10ByCategory(Category category);
	List<Book> findTop10ByOrderByModificationDateDesc();
	List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase (String stringSearchTitle, String stringSearchAuthor);
	List<Book> findAllByCategoryId(Long id);
	@Query("SELECT b FROM Book b WHERE b.category.id = :id_category AND (LOWER(b.title) LIKE LOWER (CONCAT('%', :string, '%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%', :string, '%')))")
	List<Book> findByCategoryAndTitleAuthor(@Param("id_category") Long id_category, @Param("string") String string);
}
