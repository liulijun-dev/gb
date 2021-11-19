package com.guwave.onetest.site.application.service;

import com.guwave.onetest.site.helper.domain.DomainEvent;

public interface SyncEventBus {
    void post(DomainEvent event);
    void register(Object eventHandler);
    void unregister(Object eventHandler);
}
