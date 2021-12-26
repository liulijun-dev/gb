package com.guwave.onetest.instrument.exception;

import com.guwave.onetest.common.exception.BaseRuntimeException;

public class ChannelNotFoundException extends BaseRuntimeException {
    public ChannelNotFoundException(String code, String message) {
        super(code, message);
    }
}
