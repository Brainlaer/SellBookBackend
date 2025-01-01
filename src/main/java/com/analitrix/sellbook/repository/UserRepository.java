package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{
	User findByMail (String mail);
}
