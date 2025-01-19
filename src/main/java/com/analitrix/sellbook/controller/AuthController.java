package com.analitrix.sellbook.controller;

import com.analitrix.sellbook.auth.AuthResponse;
import com.analitrix.sellbook.service.AuthService;
import com.analitrix.sellbook.dto.auth.LoginRequest;
import com.analitrix.sellbook.dto.auth.RegisterRequest;
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
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/checktoken")
    public ResponseEntity<ResponseHttp> checkTocken(){
        return ResponseEntity.ok(new ResponseHttp(200,"Token is valid"));
    }
}
