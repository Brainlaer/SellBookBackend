package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String>{
}
