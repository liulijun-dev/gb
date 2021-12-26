package com.guwave.onetest.site.application.service;

import com.guwave.onetest.site.domain.pin.event.SiteDeletedEvent;
import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.event.PinDeletedEvent;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pin.model.SiteId;
import com.guwave.onetest.site.domain.pingroup.model.PinGroup;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import com.guwave.onetest.site.domain.site.Site;
import com.guwave.onetest.site.domain.site.SiteRepository;
import com.guwave.onetest.site.exception.PinCannotFoundException;
import com.guwave.onetest.site.exception.PinNameRepeatException;
import com.guwave.onetest.site.helper.UUIDUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PinAppService {
    private final PinRepository pinRepository;
    private final SiteRepository siteRepository;
    private final PinGroupRepository pinGroupRepository;
    private final SyncEventBus eventBus;

    public PinAppService(PinRepository pinRepository,
        PinGroupRepository pinGroupRepository,
        SiteRepository siteRepository,
        SyncEventBus eventBus) {
        this.pinRepository = pinRepository;
        this.pinGroupRepository = pinGroupRepository;
        this.siteRepository = siteRepository;
        this.eventBus = eventBus;
    }

    public void createPin(String name) {
        Pin pin = Pin.create(UUIDUtil.uuid(), name);
        pinRepository.save(pin);
    }

    /**
     * 1. 同时操作两个数据源时，需要注意事务
     * 2. 在application service编排多个聚合根
     */
    @Transactional
    public void deletePin_v1(String id) {
        // delete pin
        Pin pin = pinRepository.findOne(id)
            .orElseThrow(() -> new PinCannotFoundException("can not find pin " + id));
        pinRepository.deletePin(id);

        // delete pin from group
        List<PinGroup> deletedPinPinGroup = pinGroupRepository.findAll()
            .stream()
            .filter(it -> it.hasPin(id))
            .peek(it -> it.deletePin(id))
            .collect(Collectors.toList());
        pinGroupRepository.saveBatch(deletedPinPinGroup);

        // delete pin from site
        List<Site> deletedPinSites = siteRepository.findBatch(pin.getSiteIds())
            .stream()
            .peek(it -> it.deletePin(id))
            .collect(Collectors.toList());
        siteRepository.saveBatch(deletedPinSites);
    }

    // 通过事件总线发送Pin删除的消息
    public void deletePin_v2(String id) {
        pinRepository.findOne(id)
            .orElseThrow(() -> new PinCannotFoundException("can not find pin " + id));
        pinRepository.deletePin(id);

        PinDeletedEvent pinDeletedEvent = new PinDeletedEvent(id);
        eventBus.post(pinDeletedEvent);
    }

    public void updatePinName(String id, String newName) {
        Pin pin = pinRepository.findOne(id)
            .orElseThrow(() -> new PinCannotFoundException("can not find pin " + id));
        Optional<Pin> pinHasSameNewName = pinRepository.findByName(newName);

        if (pinHasSameNewName.isPresent() && !pinHasSameNewName.get().id().equals(id)) {
            throw new PinNameRepeatException(pinHasSameNewName.get().getName() + "has the same new name");
        }

        pin.renamePin(newName);
        pinRepository.save(pin);
    }

    public void configRelationship(String id, SiteId siteId) {
        Pin pin = pinRepository.findOne(id)
            .orElseThrow(() -> new PinCannotFoundException("can not find pin " + id));
        pin.configRelationship(siteId);
        pinRepository.save(pin);
    }

    public void deleteRelationship(String id, SiteId siteId) {
        Pin pin = pinRepository.findOne(id)
            .orElseThrow(() -> new PinCannotFoundException("can not find pin " + id));
        // 可以通过返回boolean值减少对repository的操作
        boolean siteDeleted = pin.deleteRelationship(siteId);
        if (pin.deleteRelationship(siteId)) {
            PinDeletedEvent event = new PinDeletedEvent(id);
            pinRepository.save(pin);
            eventBus.post(event);
        }
    }
}
