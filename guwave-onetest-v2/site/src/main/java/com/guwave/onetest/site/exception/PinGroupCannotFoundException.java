package com.guwave.onetest.site.exception;

import com.guwave.onetest.common.exception.BaseRuntimeException;

public class PinGroupCannotFoundException extends BaseRuntimeException {
    public PinGroupCannotFoundException(String code, String message) {
        super(code, message);
    }
}
