package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.dto.user.UserFilterableColumnsEnum;
import com.analitrix.sellbook.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String>{
	User findByMail (String mail);

	@Query("SELECT u FROM User u")
	List<User> findAllUsers(Pageable pageable);

	@Query("SELECT u FROM User u WHERE u.documentNumber LIKE CONCAT('%', :filter, '%')")
	List<User> findAllBydocument(@Param("filter")Long filter, Pageable pageable);
	@Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :filter, '%'))")
	List<User> findAllByName(@Param("filter")String filter, Pageable pageable);
	@Query("SELECT u FROM User u WHERE LOWER(u.surname) LIKE LOWER(CONCAT('%', :filter, '%'))")
	List<User> findAllBySurname(@Param("filter")String filter, Pageable pageable);
	@Query("SELECT u FROM User u WHERE u.phone LIKE CONCAT('%', :filter, '%')")
	List<User> findAllByPhone(@Param("filter")Long filter, Pageable pageable);
	@Query("SELECT u FROM User u WHERE LOWER(u.mail) LIKE LOWER(CONCAT('%', :filter, '%'))")
	List<User> findAllByMail(@Param("filter")String filter, Pageable pageable);
}
