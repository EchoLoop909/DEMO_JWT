package com.example.demo.controller;

import com.example.demo.model.dto.StaffDto;
import com.example.demo.model.dto.request.ApiResponse;
import com.example.demo.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class StaffController {

    private final StaffService staffService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createStaff(@RequestBody StaffDto dto) {
        ApiResponse response = staffService.createStaff(dto);
        return ResponseEntity.ok(response);
    }
}
