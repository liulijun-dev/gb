package com.guwave.onetest.instrument.exception;

import com.guwave.onetest.common.exception.BaseRuntimeException;

public class InstrumentCannotFoundException extends BaseRuntimeException {
    public InstrumentCannotFoundException(String code, String message) {
        super(code, message);
    }
}
