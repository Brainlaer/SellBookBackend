package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.model.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
	User findByBusinessAndMail(String mail);
	Page<User> findAllByBusiness(Pageable pageable);
}
