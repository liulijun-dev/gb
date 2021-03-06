package com.guwave.onetest.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseResponse<T> {
    private static final String DEFAULT_SUCCESS_CODE = "0";
    private static final String DEFAULT_SUCCESS_MESSAGE = "success";

    private final String code;
    private final String message;
    private final T result;

    public static BaseResponse<Void> success() {
        return new BaseResponse<>(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);
    }

    public static <T> BaseResponse<T> success(T result) {
        return new BaseResponse<>(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, result);
    }

    public static BaseResponse<Void> failure(String code) {
        return new BaseResponse<Void>(code ,null, null);
    }

    public static BaseResponse<Void> failure(String code, String message) {
        return new BaseResponse<Void>(code ,message, null);
    }

    public static <T> BaseResponse<T> failure(String code,String message, T result) {
        return new BaseResponse<>(code ,message, result);
    }
}
