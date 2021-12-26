package com.guwave.onetest.site.constant;

public abstract class ErrorCode {
    private static final String PREFIX = "100";

    public static final String CONNECTION_ALREADY_EXIST = PREFIX + "0001";

    public static final String PIN_CAN_NOT_FOUND = PREFIX + "0002";

    public static final String PIN_NAME_REPEAT = PREFIX + "0003";

    public static final String PIN_GROUP_NOT_FOUND = PREFIX + "0004";

    public static final String PIN_GROUP_NAME_REPEAT = PREFIX + "0005";

    public static final String SITE_CAN_NOT_FOUND = PREFIX + "0006";
}
