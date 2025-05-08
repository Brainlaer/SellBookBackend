package com.analitrix.sellbook.repository;


import com.analitrix.sellbook.model.core.dto.ObjectFlatten;
import com.analitrix.sellbook.model.core.SubCategory;

import java.util.List;
import java.util.UUID;

public interface SubCategoryRepository {
    List<SubCategory> findAll(UUID businessId);
    List<SubCategory> findAllByCategoryId(UUID businessId, UUID categoryId);
    SubCategory findById(UUID businessId, UUID id);
    List<ObjectFlatten> findAllFlattenByCategoryId(UUID businessId, UUID categoryId);
    SubCategory save(SubCategory subCategory);
    SubCategory create(SubCategory subCategory);
}
