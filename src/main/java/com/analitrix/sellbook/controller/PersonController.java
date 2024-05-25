package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.dto.PersonDtoDelete;
import com.analitrix.sellbook.dto.PersonDtoId;
import com.analitrix.sellbook.entity.Person;
import com.analitrix.sellbook.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/sellbook/user")
@CrossOrigin(origins = "*")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/findById")
	public ResponseEntity<Person> findById(@RequestBody PersonDtoId userDtoId){
		return personService.findById(userDtoId);
	}
	
	@PostMapping("/insertUser")
	public ResponseEntity<String> insertUser(@RequestBody Person person) {
		return personService.insertUser(person);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody Person person) {
		return personService.updateUser(person);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestBody PersonDtoDelete personDtoDelete) {
		return personService.deleteUser(personDtoDelete);
	}
}
