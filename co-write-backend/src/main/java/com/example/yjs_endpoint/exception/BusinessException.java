package com.example.yjs_endpoint.exception;

public class BusinessException extends RuntimeException {
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    // 简化构造方法
    public BusinessException(String message) {
        this(400, message);
    }

    // Getter
    public int getCode() {
        return code;
    }
}
