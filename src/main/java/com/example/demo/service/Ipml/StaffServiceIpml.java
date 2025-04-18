package com.example.demo.service.Ipml;

import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.model.dto.StaffDto;
import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.model.entity.Staff;
import com.example.demo.repository.StaffRepository;
import com.example.demo.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceIpml implements StaffService {

    private static final Logger logger = LoggerFactory.getLogger(StaffServiceIpml.class);

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public ApiResponse createStaff(StaffDto dto){
        Staff staff = new Staff();

        staff.setFirstName(dto.getFirstName());
        staff.setLastName(dto.getLastName());
        staff.setPosition(dto.getPosition());
        staff.setPhoneNumber(dto.getPhoneNumber());
        staff.setAddress(dto.getAddress());
        staff.setEmployeeCode(dto.getEmployeeCode());

        staffRepository.save(staff);

        return ApiResponse.<Staff>builder()
                .code(1000)
                .message("Tạo nhân viên thành công")
                .result(staff)
                .build();

    }

    public ApiResponse deleteStaff(Long id){
        Optional<Staff> staff = staffRepository.findById(id);

        if (staff.isEmpty()) {
            throw new AppException(ErrorCode.ID_NOTFOUND);
        }

        staffRepository.deleteById(id);

        return ApiResponse.success("Staff deleted successfully");
    }

    public ApiResponse updateStaff(Long id,StaffDto dto){
        Optional<Staff> staff = staffRepository.findById(id);

        if (staff.isEmpty()) {
            throw new AppException(ErrorCode.ID_NOTFOUND);
        }

        Staff staff1 = staff.get();
        staff1.setFirstName(dto.getFirstName());
        staff1.setLastName(dto.getLastName());
        staff1.setPosition(dto.getPosition());
        staff1.setPhoneNumber(dto.getPhoneNumber());
        staff1.setAddress(dto.getAddress());
        staff1.setEmployeeCode(dto.getEmployeeCode());

        staffRepository.save(staff1);
        return ApiResponse.success("Staff updated successfully");
    }

    public ApiResponse getStaffList(String firstName, String lastName, Long id, Integer pageIdx, Integer pageSize) {
        try {
            List<Staff> results = new ArrayList<>();
            Pageable pageable = PageRequest.of(pageIdx, pageSize);
            Page<Staff> staffPage;
            if(firstName == null && lastName == null && id == null){
                staffPage = staffRepository.findAll(pageable);
                results = staffPage.getContent();
            }else{
                staffPage = staffRepository.findStaff(id, firstName != null ? firstName.trim() : null, lastName != null ? lastName.trim() : null, pageable);
                results = staffPage.getContent();
            }
            return ApiResponse.paginated("Lấy danh sách nhân viên thành công", staffPage.getContent(), staffPage, HttpStatus.OK);

        } catch (Exception e) {
            // In lỗi chi tiết nếu có
            e.printStackTrace();
            logger.error("Error occurred while fetching staff list: " + e.getMessage(), e);
            return ApiResponse.error(4001, "Invalid request");
        }
    }

}



