package com.analitrix.sellbook.auth;

import com.analitrix.sellbook.entity.Person;
import com.analitrix.sellbook.repository.PersonRepository;
import com.analitrix.sellbook.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager  authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        UserDetails user=personRepository.findByMail(request.getMail());
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        Person person = new Person();
        person.setMail(request.getMail());
        person.setId(request.getId());
        person.setName(request.getName());
        person.setSurname(request.getSurname());
        person.setPhone(request.getPhone());
        person.setHomeAddress(request.getHomeAddress());
        person.setPassword(passwordEncoder.encode(request.getPassword()));
        personRepository.save(person);
        return AuthResponse.builder()
                .token(jwtService.getToken(person))
                .build();
    }
}
