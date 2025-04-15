package com.example.demo.service;

import com.example.demo.model.dto.CustommerDto;
import com.example.demo.model.dto.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustommerService {
    ApiResponse createCustommer(CustommerDto dto);

    ApiResponse deleteCustommer(Long id);

    ApiResponse updateCustommer(Long id ,CustommerDto dto);

    ApiResponse getlist(Long id ,int pageIdx,int pageSize);
}
