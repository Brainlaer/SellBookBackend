package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
