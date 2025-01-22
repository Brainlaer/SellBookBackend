package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	User findByMail (String mail);
	Page<User> findAll(Pageable pageable);

}
