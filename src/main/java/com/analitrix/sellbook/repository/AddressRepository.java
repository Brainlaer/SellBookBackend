package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.model.core.Address;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address,UUID> {
}
