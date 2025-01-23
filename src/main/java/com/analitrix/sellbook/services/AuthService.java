package com.analitrix.sellbook.services;

import com.analitrix.sellbook.dtos.auth.AuthResponseDto;
import com.analitrix.sellbook.dtos.auth.LoginRequestDto;
import com.analitrix.sellbook.dtos.auth.RegisterRequestDto;
import com.analitrix.sellbook.models.User;
import com.analitrix.sellbook.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager  authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponseDto login(LoginRequestDto request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
        UserDetails user= userRepository.findByMail(request.getMail());
        String token=jwtService.getToken(user);
        return AuthResponseDto.builder()
                .token(token)
                .build();
    }

    public AuthResponseDto register(RegisterRequestDto request){
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
        return AuthResponseDto.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
