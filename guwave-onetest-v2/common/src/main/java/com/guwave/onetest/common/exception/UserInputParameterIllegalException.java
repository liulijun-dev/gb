package com.guwave.onetest.common.exception;

public class UserInputParameterIllegalException extends BaseRuntimeException {
    public UserInputParameterIllegalException(String code, String message) {
        super(code, message);
    }
}
