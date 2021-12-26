package com.guwave.onetest.common.exception;

public class BaseRuntimeException extends RuntimeException {
    public String code;

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseRuntimeException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
}
