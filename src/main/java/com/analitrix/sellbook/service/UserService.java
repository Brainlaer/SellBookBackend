package com.analitrix.sellbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.dto.user.*;
import com.analitrix.sellbook.entity.User;
import com.analitrix.sellbook.helpers.dto.FlattenDto;
import com.analitrix.sellbook.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	ModelMapper modelMapper=new ModelMapper();

	public ResponseEntity<ResponseHttp> findById(String id) {
		Optional<User> userOptional = userRepository.findById(id);

		if (userOptional.isEmpty()) return new ResponseEntity<>(new ResponseHttp(404,"No encontrado"), HttpStatus.NOT_FOUND);
		UserGetDto userGetDto = modelMapper.map(userOptional.get(),UserGetDto.class);

		return new ResponseEntity<>(new ResponseHttp(200, userGetDto),HttpStatus.OK);
	}

	public ResponseEntity<ResponseHttp> findUsers(UserRequestDto request){
		List<?> users;
		if(request.isFlatten()){
			users=findUsersflatten();
		}else{
			Pageable pageable = PageRequest.of(request.getOffset(), request.getLimit());
			users=findUsersAllData(request, pageable);
		}

		if(users.stream().count()==0)return new ResponseEntity<>(new ResponseHttp(404, "No encontrado"), HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(new ResponseHttp(200,users), HttpStatus.OK);
	}

	public ResponseEntity<ResponseHttp> updateUser(String id, UserPutDto userPutDto) {
		Optional<User> userOptional = userRepository.findById(id);

		if (userOptional.isEmpty()) return new ResponseEntity<>(new ResponseHttp(404, "No encontrado"), HttpStatus.NOT_FOUND);
		User user=userOptional.get();
		if(!user.getDocumentType().equals(userPutDto.getDocumentType())){
			user.setDocumentType(userPutDto.getDocumentType());
		}if(!user.getDocumentNumber().equals(userPutDto.getDocumentNumber())){
			user.setDocumentNumber(userPutDto.getDocumentNumber());
		}if(!user.getName().equals(userPutDto.getName())){
			user.setName(userPutDto.getName());
		}if(!user.getSurname().equals(userPutDto.getSurname())){
			user.setSurname(userPutDto.getSurname());
		}if(!user.getPhone().equals(userPutDto.getPhone())){
			user.setPhone(userPutDto.getPhone());
		}if(!user.getMail().equals(userPutDto.getMail())){
			user.setMail(userPutDto.getMail());
		}if(!user.getHomeAddress().equals(userPutDto.getHomeAddress())){
			user.setHomeAddress(userPutDto.getHomeAddress());
		}if(!userPutDto.getPassword().isEmpty()){
			user.setPassword(passwordEncoder.encode(userPutDto.getPassword()));
		}
		userRepository.save(user);
		return new ResponseEntity<>(new ResponseHttp(200, "Actualizado con exito"), HttpStatus.OK);
	}

	public ResponseEntity<ResponseHttp> deleteUser(String id, UserDeleteDto userDeleteDto) {
		Optional<User> userOptional = userRepository.findById(id);
		if(userOptional.isEmpty()) return new ResponseEntity<>(new ResponseHttp(404,"No encontrado"), HttpStatus.NOT_FOUND);

		User user = userOptional.get();
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getMail(), userDeleteDto.getPassword()));

		userRepository.deleteById(id);
		return new ResponseEntity<>(new ResponseHttp(200,"Eliminado con exito"), HttpStatus.OK);

	}

	public List<UserGetDto> findUsersAllData(UserRequestDto request, Pageable pageable){
		List<User> users = new ArrayList<>();
		List<UserGetDto> usersDto = new ArrayList<>();
		if(request.getFilterBy()!=null){
//			switch (request.getFilterBy()) {
//				case DOCUMENT_NUMBER :
//					users = userRepository.findAllBydocument(Long.parseLong(request.getFilter()), pageable);
//					break;
//				case NAME:
//					users = userRepository.findAllByName(request.getFilter(), pageable);
//					break;
//				case SURNAME:
//					users = userRepository.findAllBySurname(request.getFilter(), pageable);
//					break;
//				case PHONE:
//					users = userRepository.findAllByPhone(Long.parseLong(request.getFilter()), pageable);
//					break;
//				case MAIL:
//					users = userRepository.findAllByMail(request.getFilter(), pageable);
//					break;
//			}
		}else {
			users = userRepository.findAllUsers(pageable);
		}
			if(users.stream().count()>0){
				for(User user : users){
					UserGetDto userGetDto=modelMapper.map(user,UserGetDto.class);
					usersDto.add(userGetDto);
				}
			}
			return usersDto;
	}

	public List<FlattenDto> findUsersflatten(){
		List<User> users=userRepository.findAll();
		List<FlattenDto> usersDto = new ArrayList<>();
		System.out.println("4");
		if(users.stream().count()>0){
			for(User user : users){
				FlattenDto flattenDto =modelMapper.map(user, FlattenDto.class);
				usersDto.add(flattenDto);
			}
		}
		return usersDto;
	}

}