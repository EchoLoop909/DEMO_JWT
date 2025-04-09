package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserResponse {
    String username;

    public UserResponse(String username) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    String password;
    String email;
}
