package com.guwave.onetest.site.infrastructure.message;

import com.google.common.eventbus.EventBus;
import com.guwave.onetest.site.application.service.SyncEventBus;
import com.guwave.onetest.site.helper.domain.DomainEvent;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2(topic = "SyncEventBusImpl")
public class SyncEventBusImpl implements SyncEventBus {
    private static final SyncEventBusImpl instance = new SyncEventBusImpl();
    private final EventBus eventBus;

    private SyncEventBusImpl() {
        this.eventBus = new EventBus();
    }

    public static SyncEventBusImpl getInstance() {
        return instance;
    }

    @Override
    public void post(DomainEvent event) {
        if (Objects.isNull(event)) {
            log.info("event is null, cannot publish");
            return;
        }

        eventBus.post(event);
    }

    @Override
    public void register(Object eventHandler) {
        if (Objects.isNull(eventHandler)) {
            log.info("event handler is null, cannot register");
            return;
        }
        eventBus.register(eventHandler);
    }

    @Override
    public void unregister(Object eventHandler) {
        if (Objects.isNull(eventHandler)){
            log.info("event handler is null, cannot unregister");
            return;
        }
        eventBus.unregister(eventHandler);
    }
}
