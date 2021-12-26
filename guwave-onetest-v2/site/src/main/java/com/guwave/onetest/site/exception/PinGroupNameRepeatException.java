package com.guwave.onetest.site.exception;

import com.guwave.onetest.common.exception.BaseRuntimeException;

public class PinGroupNameRepeatException extends BaseRuntimeException {
    public PinGroupNameRepeatException(String code, String message) {
        super(code, message);
    }
}
