package com.analitrix.sellbook.repositories;

import com.analitrix.sellbook.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String>{
    Optional<Category> findByName(String name);
}
