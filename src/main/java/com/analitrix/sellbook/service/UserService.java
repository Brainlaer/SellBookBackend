package com.analitrix.sellbook.service;

import java.util.Optional;

import com.analitrix.sellbook.dto.UserDtoDelete;
import com.analitrix.sellbook.dto.UserDtoId;
import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.entity.User;
import com.analitrix.sellbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<User> findById(UserDtoId userId) {
		Optional<User> user = userRepository.findById(userId.getId());

		if (user.isPresent()) {
			User userFound = user.get();
			return new ResponseEntity<>(userFound, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> insertUser(User user) {

		if (userRepository.findById(user.getId()).isEmpty()) {
			userRepository.save(user);
			return new ResponseEntity<>("Insertado con exito", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se pudó insertar", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public ResponseEntity<String> updateUser(User user) {
		Optional<User> userOptional = userRepository.findById(user.getId());

		if (userOptional.isPresent()) {
			userRepository.save(user);
			return new ResponseEntity<>("¡Actualizado con exito!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> deleteUser(UserDtoDelete userDtoDelete) {
		Optional<User> user = userRepository.findById(userDtoDelete.getId());

		if (user.isPresent()) {
			User userFound = user.get();
			if(userDtoDelete.getMail().equals(userFound.getMail())&&userDtoDelete.getPassword().equals(userFound.getPassword())){
				userRepository.deleteById(userDtoDelete.getId());
				return new ResponseEntity<>("¡Eliminado con exito!", HttpStatus.OK);
			}else{
				return new ResponseEntity<>("Autenticación invalida", HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>("No se pudó eliminar", HttpStatus.NOT_FOUND);
		}
	}

}