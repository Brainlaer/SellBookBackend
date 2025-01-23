package com.analitrix.sellbook.controllers;

import com.analitrix.sellbook.dtos.common.ResponseHttp;
import com.analitrix.sellbook.dtos.user.UserDeleteDto;
import com.analitrix.sellbook.dtos.user.UserUpdateDto;
import com.analitrix.sellbook.dtos.user.UserRequestDto;
import com.analitrix.sellbook.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	public ResponseEntity<?> findOne(@PathVariable String id){
		return userService.findOne(id);
	}

	ModelMapper modelMapper=new ModelMapper();

	@GetMapping("")
	public Page<?> findMany(@ParameterObject UserRequestDto userRequestDto){
		return userService.findMany(userRequestDto);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ResponseHttp> update(
			@PathVariable String id,
			@RequestBody UserUpdateDto userUpdateDto) {
		return userService.update(id, userUpdateDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseHttp> delete(
			@PathVariable String id,
			@RequestBody UserDeleteDto userDeleteDto) {
		return userService.delete(id, userDeleteDto);
	}
}
