package com.example.demo.service;

import com.example.demo.model.dto.StaffDto;
import com.example.demo.model.dto.response.ApiResponse;

public interface StaffService {
    ApiResponse createStaff(StaffDto dto);

    ApiResponse deleteStaff(Long id);

    ApiResponse updateStaff(Long id,StaffDto dto);

    ApiResponse getStaffList(String firstName,String lastName,Long id,Integer pageIdx,Integer pageSize);
}
