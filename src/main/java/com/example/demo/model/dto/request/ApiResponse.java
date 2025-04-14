package com.example.demo.model.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class ApiResponse<T> {
//    @Builder.Default
//    private int code = 1000;
//
//    private String message;
//    private T result;
//}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @Builder.Default
    int code = 1000;
    String message;
    T result;

    public ApiResponse(String requestSuccessful, Object data, long totalElements, int totalPages, HttpStatus status) {
    }

    // --- Static method cho thành công không có dữ liệu ---
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .code(1000)
                .message(message)
                .build();
    }

    // --- Static method cho thành công có dữ liệu ---
    public static <T> ApiResponse<T> success(String message, T result) {
        return ApiResponse.<T>builder()
                .code(1000)
                .message(message)
                .result(result)
                .build();
    }

    // --- Static method cho thất bại ---
    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    // Phương thức để tạo response thành công
    public static ApiResponse getResponses(Object data, long totalElements, int totalPages, HttpStatus status) {
        return new ApiResponse("Request successful", data, totalElements, totalPages, status);
    }

    // Getters và Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}