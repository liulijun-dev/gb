package com.guwave.onetest.common.exception;

public class SystemException extends RuntimeException {
    public String code;

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
}
