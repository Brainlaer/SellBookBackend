package com.analitrix.sellbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.analitrix.sellbook.dto.SortEnum;
import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import com.analitrix.sellbook.dto.user.*;
import com.analitrix.sellbook.entity.User;
import com.analitrix.sellbook.helpers.dto.FlattenDto;
import com.analitrix.sellbook.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

	public ResponseEntity<ResponseHttp> findOne(String id) {
		Optional<User> userOptional = userRepository.findById(id);

		if (userOptional.isEmpty()) return new ResponseEntity<>(new ResponseHttp(404,"No encontrado"), HttpStatus.NOT_FOUND);
		UserGetDto userGetDto = modelMapper.map(userOptional.get(),UserGetDto.class);

		return new ResponseEntity<>(new ResponseHttp(200, userGetDto),HttpStatus.OK);
	}

	public Page<?> findAll(UserRequestDto request){
		Sort sort = null;
		if(request.getSort().equals(SortEnum.ASC)) {
			sort = Sort.by(Sort.Order.asc(request.getSortableColumn().toString()));
		}else if(request.getSort().equals(SortEnum.DESC)){
			sort = Sort.by(Sort.Order.desc(request.getSortableColumn().toString()));
		}
		Pageable pageable= PageRequest.of(request.getOffset(), request.getLimit(),sort);
		Specification<User> spec = UserSpecifications.filterBy(request.getDocumentNumber(),request.getName(),request.getUsername());
		return userRepository.findAll(spec, pageable);
	}

	public ResponseEntity<ResponseHttp> update(String id, UserPutDto userPutDto) {
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

	public ResponseEntity<ResponseHttp> delete(String id, UserDeleteDto userDeleteDto) {
		Optional<User> userOptional = userRepository.findById(id);
		if(userOptional.isEmpty()) return new ResponseEntity<>(new ResponseHttp(404,"No encontrado"), HttpStatus.NOT_FOUND);

		User user = userOptional.get();
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getMail(), userDeleteDto.getPassword()));

		userRepository.deleteById(id);
		return new ResponseEntity<>(new ResponseHttp(200,"Eliminado con exito"), HttpStatus.OK);

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