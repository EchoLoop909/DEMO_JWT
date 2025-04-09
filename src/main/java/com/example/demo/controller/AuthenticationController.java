package com.example.demo.controller;

import com.example.demo.model.dto.request.Login;
import com.example.demo.model.dto.request.Register;
import com.example.demo.model.dto.response.AuthResponse;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody Login request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Register request) {
        authService.register(request);
        return ResponseEntity.ok("Đăng ký thành công");
    }
}
