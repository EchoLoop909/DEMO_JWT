package com.example.demo.service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.response.UserResponse;
import org.springframework.stereotype.Service;

public interface UserService {
    UserResponse createUser(AuthenticationRequest authenticationRequest);
}
