package com.guwave.onetest.site.exception;

import com.guwave.onetest.common.exception.BaseRuntimeException;

public class PinNameRepeatException extends BaseRuntimeException {

    public PinNameRepeatException(String code, String message) {
        super(code, message);
    }
}
