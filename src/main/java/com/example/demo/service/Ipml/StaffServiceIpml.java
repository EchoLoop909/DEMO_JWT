package com.example.demo.service.Ipml;

import com.example.demo.model.dto.StaffDto;
import com.example.demo.model.dto.request.ApiResponse;
import com.example.demo.model.entity.Staff;
import com.example.demo.repository.StaffRepository;
import com.example.demo.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceIpml implements StaffService {

    private static final Logger logger = LoggerFactory.getLogger(StaffServiceIpml.class);

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public ApiResponse createStaff(StaffDto dto){
        Staff staff = new Staff();

        staff.setChucvu(dto.getChucvu());
        staff.setDiachi(dto.getDiachi());
        staff.setHo(dto.getHo());
        staff.setTen(dto.getTen());
        staff.setManhanvien(dto.getManhanvien());
        staff.setSodienthoai(dto.getSodienthoai());

        staffRepository.save(staff);

        return ApiResponse.<Staff>builder()
                .code(1000)
                .message("Tạo nhân viên thành công")
                .result(staff)
                .build();

    }


}
