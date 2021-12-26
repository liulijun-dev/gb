package com.guwave.onetest.common.exception;

public class SystemException extends BaseRuntimeException {
    public SystemException(String code, String message) {
        super(code, message);
    }

    public SystemException(String code, String message, Throwable e) {
        super(code, message, e);
    }
}
