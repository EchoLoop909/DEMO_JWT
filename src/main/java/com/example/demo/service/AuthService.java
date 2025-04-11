package com.example.demo.service;

import com.example.demo.auth.AuthenticationResponse;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.model.dto.request.Register;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest request);
}
