package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "User already existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1003, "Unauthorized", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(1004, "Access denied", HttpStatus.FORBIDDEN),
    INTERNAL_SERVER_ERROR(9999, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus status;
}
