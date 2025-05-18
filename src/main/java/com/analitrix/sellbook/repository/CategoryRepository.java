package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.model.core.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>{
    List<Category> findAllByBusiness (UUID businessId);
    Optional<Category> findByBusinessAndId(UUID businessId, UUID id);
}
