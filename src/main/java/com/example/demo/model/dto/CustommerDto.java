package com.example.demo.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustommerDto {
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String identityNumber;
}
