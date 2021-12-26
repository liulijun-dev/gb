package com.guwave.onetest.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}
