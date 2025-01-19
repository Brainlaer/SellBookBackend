package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.dto.user.UserDeleteDto;
import com.analitrix.sellbook.dto.user.UserPutDto;
import com.analitrix.sellbook.dto.user.UserRequestDto;
import com.analitrix.sellbook.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
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

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable String id){
		return userService.findById(id);
	}

	ModelMapper modelMapper=new ModelMapper();

	@GetMapping("")
	public ResponseEntity<?> findUsers(
			@ParameterObject UserRequestDto userRequestDto
			){
		return userService.findUsers(userRequestDto);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ResponseHttp> updateUser(
			@PathVariable String id,
			@RequestBody UserPutDto userPutDto) {
		return userService.updateUser(id,userPutDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseHttp> deleteUser(
			@PathVariable String id,
			@RequestBody UserDeleteDto userDeleteDto) {
		return userService.deleteUser(id, userDeleteDto);
	}
}
