package com.guwave.onetest.site.exception;

import com.guwave.onetest.common.exception.BaseRuntimeException;

public class ConnectionAlreadyExistException extends BaseRuntimeException {
    public ConnectionAlreadyExistException(String code, String message) {
        super(code, message);
    }
}
