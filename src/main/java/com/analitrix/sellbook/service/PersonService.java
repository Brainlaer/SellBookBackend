package com.analitrix.sellbook.service;

import java.util.Optional;

import com.analitrix.sellbook.dto.PersonDtoDelete;
import com.analitrix.sellbook.dto.PersonDtoId;
import com.analitrix.sellbook.entity.Person;
import com.analitrix.sellbook.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public ResponseEntity<Person> findById(PersonDtoId personDtoId) {
		Optional<Person> person = personRepository.findById(personDtoId.getId());

		if (person.isPresent()) {
			Person personFound = person.get();
			return new ResponseEntity<>(personFound, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> insertUser(Person person) {

		if (personRepository.findById(person.getId()).isEmpty()) {
			personRepository.save(person);
			return new ResponseEntity<>("Insertado con exito", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se pudó insertar", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public ResponseEntity<String> updateUser(Person person) {
		Optional<Person> userOptional = personRepository.findById(person.getId());

		if (userOptional.isPresent()) {
			personRepository.save(person);
			return new ResponseEntity<>("¡Actualizado con exito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> deleteUser(PersonDtoDelete personDtoDelete) {
		Optional<Person> person = personRepository.findById(personDtoDelete.getId());

		if (person.isPresent()) {
			Person userFound = person.get();
			if(personDtoDelete.getMail().equals(userFound.getMail())&&personDtoDelete.getPassword().equals(userFound.getPassword())){
				personRepository.deleteById(personDtoDelete.getId());
				return new ResponseEntity<>("¡Eliminado con exito!", HttpStatus.OK);
			}else{
				return new ResponseEntity<>("Autenticación invalida", HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>("No se pudó eliminar", HttpStatus.NOT_FOUND);
		}
	}

}