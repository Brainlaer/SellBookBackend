package com.analitrix.sellbook.service;

import com.analitrix.sellbook.security.auth.AuthResponse;
import com.analitrix.sellbook.model.core.dto.auth.LoginRequest;
import com.analitrix.sellbook.model.core.dto.auth.RegisterRequest;
import com.analitrix.sellbook.model.security.User;
import com.analitrix.sellbook.repository.UserRepository;
import com.analitrix.sellbook.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager  authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        UserDetails user= userRepository.findByMail(request.getMail());
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        User user = new User();
        user.setMail(request.getMail());
        user.setDocumentNumber(request.getDocumentNumber());
        user.setDocumentType(request.getDocumentType());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPhone(request.getPhone());
        user.setHomeAddress(request.getHomeAddress());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
