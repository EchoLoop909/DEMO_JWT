package com.example.demo.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String messageDetail;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.messageDetail = errorCode.getMessage(); // 👈 Gán mặc định
    }

    public AppException(ErrorCode errorCode, String messageDetail) {
        super(messageDetail);
        this.errorCode = errorCode;
        this.messageDetail = messageDetail;
    }
}
