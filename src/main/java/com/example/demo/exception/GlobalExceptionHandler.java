package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception ex) {
        ex.printStackTrace(); // log lỗi ra console
        ErrorCode code = ErrorCode.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ErrorResponse(code.getCode(), code.getMessage()), code.getStatus());
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse response = new ErrorResponse(errorCode.getCode(), ex.getMessageDetail());
        return new ResponseEntity<>(response, errorCode.getStatus());
    }

}
