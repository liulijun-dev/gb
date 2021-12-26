package com.guwave.onetest.site.domain.pin.event;

import com.guwave.onetest.site.helper.domain.DomainEvent;

public class SiteDeletedEvent extends DomainEvent {
    private String siteId;
    public SiteDeletedEvent(String id) {
        super(id);
    }

    @Override
    protected String getEventType() {
        return null;
    }
}
