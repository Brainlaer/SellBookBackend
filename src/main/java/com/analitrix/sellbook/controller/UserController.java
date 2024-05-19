package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.dto.UserDtoDelete;
import com.analitrix.sellbook.dto.UserDtoId;
import com.analitrix.sellbook.entity.User;
import com.analitrix.sellbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/sellbook/usuario")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/findById")
	public ResponseEntity<User> findById(@RequestBody UserDtoId userDtoId){
		return userService.findById(userDtoId);
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
