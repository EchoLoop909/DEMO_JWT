package com.example.demo.controller;

import com.example.demo.model.dto.StaffDto;
import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/")
@RequiredArgsConstructor

public class StaffController {

    private final StaffService staffService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createStaff(@RequestBody StaffDto dto) {
        ApiResponse response = staffService.createStaff(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteStaff(@PathVariable Long id) {
        ApiResponse response = staffService.deleteStaff(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateStaff(@PathVariable Long id,
                                                   @RequestBody StaffDto dto) {
        ApiResponse apiResponse = staffService.updateStaff(id,dto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getlist")
    public ResponseEntity<ApiResponse> getStaffList(@RequestParam(required = false) String firstName,
                                                    @RequestParam(required = false) String lastName,
                                                    @RequestParam(required = false) Long id,
                                                    @RequestParam(defaultValue = "1") int pageIdx,
                                                    @RequestParam(defaultValue = "100") int pageSize) {
        ApiResponse apiResponse = staffService.getStaffList(firstName,lastName,id,pageIdx -1,pageSize);
        return ResponseEntity.ok(apiResponse);
    }
}
