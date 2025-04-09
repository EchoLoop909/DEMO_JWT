package com.example.demo.model.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
    }
}
