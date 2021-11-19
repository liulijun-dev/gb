package com.guwave.onetest.site.domain.pin.event;

import com.guwave.onetest.site.helper.UUIDUtil;
import com.guwave.onetest.site.helper.domain.DomainEvent;
import org.apache.commons.lang3.StringUtils;

public class PinDeletedEvent extends DomainEvent {
    private String pinId;

    public PinDeletedEvent(String pinId) {
        super(UUIDUtil.uuid());

        if (StringUtils.isBlank(pinId)) {
            throw new IllegalArgumentException("pin id can not be blank");
        }

        this.pinId = pinId;
    }

    public String pinId() {
        return pinId;
    }

    @Override
    protected String getEventType() {
        return "PinDeletedEven";
    }
}
