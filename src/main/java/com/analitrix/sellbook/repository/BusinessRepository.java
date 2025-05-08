package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.model.core.Business;
import org.hibernate.mapping.Any;

import java.util.Optional;
import java.util.UUID;

public interface BusinessRepository {
    Optional<Business> create(Business business);
    Optional<Business> save(Business business);
    Optional<Business> findById(UUID businessId);
    Boolean existsBy (String attribute, Object value);
}
