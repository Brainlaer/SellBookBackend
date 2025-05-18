package com.analitrix.sellbook.repository;

import java.util.Optional;

import com.analitrix.sellbook.model.core.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaSpecificationExecutor<Product> {

	Optional<Product> findById(Long id);
	Page<Product> findAll(Pageable pageable);

}
