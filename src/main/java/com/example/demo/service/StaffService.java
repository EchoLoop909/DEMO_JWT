package com.example.demo.service;

import com.example.demo.model.dto.StaffDto;
import com.example.demo.model.dto.request.ApiResponse;
import org.springframework.stereotype.Service;

public interface StaffService {
    ApiResponse createStaff(StaffDto dto);
}
