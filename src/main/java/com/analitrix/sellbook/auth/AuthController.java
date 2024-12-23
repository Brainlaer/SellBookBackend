package com.analitrix.sellbook.auth;

import com.analitrix.sellbook.dto.ResponseHttp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Auth")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/checktoken")
    public ResponseEntity<ResponseHttp> checkTocken(){
        return ResponseEntity.ok(new ResponseHttp("OK","Token is valid"));
    }
}
