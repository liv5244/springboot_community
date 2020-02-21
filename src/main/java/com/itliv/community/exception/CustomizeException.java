package com.itliv.community.exception;

public class CustomizeException extends RuntimeException {
    private String message;
    private int code;

    public CustomizeException(ICustomizeErrorCode customizeErrorCode) {
        this.message = customizeErrorCode.getMessage();
        this.code = customizeErrorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
