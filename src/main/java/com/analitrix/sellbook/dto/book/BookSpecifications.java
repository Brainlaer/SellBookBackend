package com.analitrix.sellbook.dto.book;

import com.analitrix.sellbook.entity.Book;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookSpecifications {
    public static Specification<Book> filterBy(String isxn, String title, String author, String editorial, String category) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (isxn != null && !isxn.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("isxn").as(String.class)), "%" + isxn.toLowerCase() + "%"));
            }
            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (author != null && !author.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
            }
            if (editorial != null && !editorial.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("editorial")), "%" + editorial.toLowerCase() + "%"));
            }
            if (category != null && !category.isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("category").as(String.class)), category.toLowerCase()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
