package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	User findByMail (String mail);
	Page<User> findAll(Pageable pageable);

}
