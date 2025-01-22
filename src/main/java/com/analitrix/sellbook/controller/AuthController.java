package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.auth.AuthResponseDto;
import com.analitrix.sellbook.service.AuthService;
import com.analitrix.sellbook.dto.auth.LoginRequestDto;
import com.analitrix.sellbook.dto.auth.RegisterRequestDto;
import com.analitrix.sellbook.helpers.dto.ResponseHttp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Auth")
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request){
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/checktoken")
    public ResponseEntity<ResponseHttp> checkTocken(){
        return ResponseEntity.ok(new ResponseHttp(200,"Token is valid"));
    }
}
