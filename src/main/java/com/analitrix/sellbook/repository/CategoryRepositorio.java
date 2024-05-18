package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositorio extends JpaRepository<Category, Long>{
	
}
