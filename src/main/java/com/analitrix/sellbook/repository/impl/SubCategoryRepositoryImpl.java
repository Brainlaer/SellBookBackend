package com.analitrix.sellbook.repository.impl;

import com.analitrix.sellbook.model.core.dto.ObjectFlatten;
import com.analitrix.sellbook.model.core.SubCategory;
import com.analitrix.sellbook.repository.SubCategoryRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SubCategoryRepositoryImpl implements SubCategoryRepository {

    private final EntityManager entityManager;

    public SubCategoryRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<SubCategory> findAll(UUID businessId) {
        String jpql = "SELECT s FROM SubCategory s WHERE s.business.id = :businessId";
         return entityManager.createQuery(jpql, SubCategory.class)
                .setParameter("businessId", businessId)
                .getResultList();
    }

    @Override
    public List<SubCategory> findAllByCategoryId(UUID businessId, UUID categoryId) {
        String jpql = "SELECT s FROM SubCategory s WHERE s.business.id = :businessId AND s.category.id =:categoryId";
        return entityManager.createQuery(jpql, SubCategory.class)
                .setParameter("businessId", businessId)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public SubCategory findById(UUID businessId, UUID id) {
        String jpql = "SELECT s FROM SubCategory s WHERE s.business.id = :businessId AND s.id =:id";
        return entityManager.createQuery(jpql, SubCategory.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ObjectFlatten> findAllFlattenByCategoryId(UUID businessId, UUID categoryId) {
        String jpql = "SELECT s.id, s.name FROM SubCategory s WHERE s.business.id = :businessId AND s.category.id =:categoryId";
        return entityManager.createQuery(jpql, ObjectFlatten.class)
                .setParameter("businessId", businessId)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public SubCategory save(SubCategory subCategory) {
        SubCategory exists= entityManager.find(SubCategory.class, subCategory.getId());

        if(exists==null){
            entityManager.persist(subCategory);
            return subCategory;
        }else{
            return entityManager.merge(subCategory);
        }

    }

    @Override
    public SubCategory create(SubCategory subCategory) {
        entityManager.persist(subCategory);
        return subCategory;
    }
}
