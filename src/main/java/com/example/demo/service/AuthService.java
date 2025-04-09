package com.example.demo.service;

import com.example.demo.model.dto.request.Register;

public interface AuthService {
    String login(String username, String password);

    String register(Register request);
}
