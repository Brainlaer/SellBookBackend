package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.dto.UserDtoDelete;
import com.analitrix.sellbook.entity.User;
import com.analitrix.sellbook.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="User")
@RestController
@RequestMapping ("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;

//	@GetMapping("/{id}")
//	public ResponseEntity<Person> findById(@PathVariable Long id){
//		return userService.findById(id);
//	}

	@GetMapping("/{mail}")
	public ResponseEntity<User> findByMail(@PathVariable String mail){
		return userService.findByMail(mail);
	}
	
	@PostMapping("/insertUser")
	public ResponseEntity<String> insertUser(@RequestBody User user) {
		return userService.insertUser(user);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestBody UserDtoDelete userDtoDelete) {
		return userService.deleteUser(userDtoDelete);
	}
}
