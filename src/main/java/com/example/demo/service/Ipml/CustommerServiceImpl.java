package com.example.demo.service.Ipml;

import com.example.demo.model.dto.CustommerDto;
import com.example.demo.model.dto.response.ApiResponse;
import com.example.demo.model.entity.Custommer;
import com.example.demo.repository.CustommerRepository;
import com.example.demo.service.CustommerService;
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
public class CustommerServiceImpl implements CustommerService {

    private static final Logger logger = LoggerFactory.getLogger(CustommerServiceImpl.class);

    @Autowired
    private CustommerRepository custommerRepository;

    public ApiResponse createCustommer(CustommerDto dto){
        try{
            Optional<Custommer> custommerFullName = custommerRepository.findByFullName(dto.getFullName());
            if(custommerFullName.isPresent()){
                return ApiResponse.error(4001, "FullName has found ");

            }

            Custommer custommer = new Custommer();
            custommer.setFullName(dto.getFullName());
            custommer.setPhone(dto.getPhone());
            custommer.setEmail(dto.getEmail());
            custommer.setAddress(dto.getAddress());
            custommer.setIdentityNumber(String.valueOf(dto.getIdentityNumber()));

            custommerRepository.save(custommer);
            return ApiResponse.<Custommer>builder().code(1000).message("Tạo custommer thành công").result(custommer).build();

        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error occurred while fetching staff list: " + e.getMessage(), e);
            return ApiResponse.error(4001, "Invalid request");
        }
    }

    public ApiResponse deleteCustommer(Long id ){
        try{
            Custommer custommer = custommerRepository.findById(id).orElse(null);

            if(custommer == null){
                return ApiResponse.error(1001, "Custommer not found");
            }

            custommerRepository.delete(custommer);
            return ApiResponse.<Custommer>builder().code(1000).message("Delete custommer thành công").build();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error occurred while fetching staff list: " + e.getMessage(), e);
            return ApiResponse.error(4001, "Invalid request");
        }
    }

    public ApiResponse updateCustommer(Long id ,CustommerDto dto){
        try{

            Optional<Custommer> custommer = custommerRepository.findById(id);
            if(custommer == null){
                return ApiResponse.error(1001, "Custommer not found");
            }

            Custommer custommerDto = custommer.get();

            custommerDto.setFullName(dto.getFullName());
            custommerDto.setPhone(dto.getPhone());
            custommerDto.setEmail(dto.getEmail());
            custommerDto.setAddress(dto.getAddress());
            custommerDto.setIdentityNumber(dto.getIdentityNumber());

            custommerRepository.save(custommerDto);
            return  ApiResponse.<Custommer>builder().code(1000).message("Update custommer thành công").result(custommerDto).build();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error occurred while fetching staff list: " + e.getMessage(), e);
            return ApiResponse.error(4001, "Invalid request");
        }
    }

    public ApiResponse getlist(Long id , int pageIdx, int pageSize){
        try{

            List<Custommer> results = new ArrayList<>();
            Pageable pageable = PageRequest.of(pageIdx, pageSize);
            Page<Custommer> custommerPage;

            if(id == null){
                custommerPage = custommerRepository.findAll(pageable);
                results = custommerPage.getContent();
            }else{
                custommerPage = custommerRepository.findCustommer(id, pageable);
                results = custommerPage.getContent();
            }
            return ApiResponse.paginated("Lấy danh sách custommer thành công", custommerPage.getContent(), custommerPage, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error occurred while fetching staff list: " + e.getMessage(), e);
            return ApiResponse.error(4001, "Invalid request");
        }
    }
}
