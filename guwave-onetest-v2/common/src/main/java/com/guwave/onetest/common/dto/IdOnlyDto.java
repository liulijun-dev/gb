package com.guwave.onetest.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IdOnlyDto<T> {
    private T id;

    public static <T> IdOnlyDto<T> instance(T id) {
        return new IdOnlyDto<>(id);
    }
}
