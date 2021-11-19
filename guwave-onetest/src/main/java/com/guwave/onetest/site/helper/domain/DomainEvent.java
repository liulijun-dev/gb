package com.guwave.onetest.site.helper.domain;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

public abstract class DomainEvent {
    private final String id;
    private final LocalDateTime createDateTime;
    private String type;

    public DomainEvent(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("event id can not be blank");
        }
        this.id = id;
        this.createDateTime = LocalDateTime.now();
        this.type = getEventType();
    }

    public String id() {
        return this.id;
    }

    public LocalDateTime happenTime() {
        return this.createDateTime;
    }

    public String eventType() {
        return this.type;
    }

    protected abstract String getEventType();
}
