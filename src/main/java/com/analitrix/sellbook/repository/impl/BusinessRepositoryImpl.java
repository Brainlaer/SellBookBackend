package com.analitrix.sellbook.repository.impl;

import com.analitrix.sellbook.model.core.Business;
import com.analitrix.sellbook.repository.BusinessRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class BusinessRepositoryImpl implements BusinessRepository {

    private final EntityManager entityManager;

    public BusinessRepositoryImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public Optional<Business> create(Business business) {
        entityManager.persist(business);
        return Optional.ofNullable(business);
    }

    @Override
    public Optional<Business> save(Business business) {
        Business exists = entityManager.find(Business.class, business.getId());
         if(exists == null){
             entityManager.persist(business);
         }else{
              entityManager.merge(business);
         }
        return Optional.of(business);

    }

    @Override
    public Optional<Business> findById(UUID businessId) {
        String jpql = "SELECT s FROM SubCategory s WHERE s.id = :businessId";
        Business business = entityManager.createQuery(jpql, Business.class)
                .setParameter("id", businessId)
                .getSingleResult();
        return Optional.ofNullable(business);
    }

    @Override
    public Boolean existsBy(String attribute, Object value) {
        String jpql = "SELECT 1 FROM Business b WHERE " + attribute + " = :value";
        Business business = entityManager.createQuery(jpql, Business.class)
                .setParameter("value", value)
                .getSingleResult();
        return business!=null;
    }
}
