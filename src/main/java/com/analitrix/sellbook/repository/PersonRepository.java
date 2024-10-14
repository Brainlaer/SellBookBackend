package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{
	Person findByMail (String mail);
}
