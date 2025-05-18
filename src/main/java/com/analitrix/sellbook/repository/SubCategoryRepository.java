package com.analitrix.sellbook.repository;


import com.analitrix.sellbook.model.core.dto.FlattenDto;
import com.analitrix.sellbook.model.core.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {
    List<SubCategory> findAllByBusiness(UUID businessId);
    List<SubCategory> findAllByBusinessAndCategory(UUID businessId, UUID categoryId);
    Optional<SubCategory> findByBusinessAndId(UUID businessId, UUID id);
}
