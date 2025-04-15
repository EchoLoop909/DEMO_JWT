package com.example.demo.controller;

import com.example.demo.model.dto.CustommerDto;
import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.service.CustommerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/custommer/")
@RequiredArgsConstructor
public class CustommerController {

    public final CustommerService custommerService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCustommer(@RequestBody CustommerDto dto){
        ApiResponse response = custommerService.createCustommer(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCustommer(@PathVariable Long id){
        ApiResponse apiResponse = custommerService.deleteCustommer(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCustommer(@PathVariable Long id,
                                                       @RequestBody CustommerDto dto){
        ApiResponse apiResponse = custommerService.updateCustommer(id,dto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getlist")
    public ResponseEntity<ApiResponse> getCustommerList(@PathVariable(required = false) Long id,
                                                        @RequestParam(defaultValue = "1") int pageIdx,
                                                        @RequestParam(defaultValue = "100") int pageSize){
        ApiResponse apiResponse = custommerService.getlist(id,pageIdx -1,pageSize);
        return ResponseEntity.ok(apiResponse);
    }
}
