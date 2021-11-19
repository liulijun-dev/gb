package com.guwave.onetest.site.application.service;

import com.google.common.eventbus.Subscribe;
import com.guwave.onetest.site.domain.pin.event.PinDeletedEvent;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pingroup.model.PinGroup;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import com.guwave.onetest.site.exception.PinCannotFoundException;
import com.guwave.onetest.site.exception.PinGroupCannotFoundException;
import com.guwave.onetest.site.helper.UUIDUtil;

import java.util.List;
import java.util.stream.Collectors;

public class PinGroupAppService {
    private final PinGroupRepository pinGroupRepository;
    private final PinRepository pinRepository;

    public PinGroupAppService(PinGroupRepository pinGroupRepository, PinRepository pinRepository) {
        this.pinGroupRepository = pinGroupRepository;
        this.pinRepository = pinRepository;
    }

    public String createPinGroup(String name) {
        PinGroup pinGroup = PinGroup.create(UUIDUtil.uuid(), name);
        pinGroupRepository.save(pinGroup);
        return pinGroup.id();
    }

    public void deletePinGroup(String id) {
        pinGroupRepository.deletePin(id);

    }

    public void renamePinGroup(String id, String newName) {
        PinGroup pinGroup = pinGroupRepository.findOne(id)
            .orElseThrow(() -> new PinGroupCannotFoundException("can not find pin group " + id));

        pinGroup.renamePinGroup(newName);

        pinGroupRepository.save(pinGroup);
    }

    public void addPin(String pinGroupId, String pinId) {
        PinGroup pinGroup = pinGroupRepository.findOne(pinGroupId)
            .orElseThrow(() -> new PinGroupCannotFoundException("can not find pin group " + pinGroupId));
        pinRepository.findOne(pinId).
            orElseThrow(() -> new PinCannotFoundException("can not find pin " + pinId));

        pinGroup.addPin(pinId);

        pinGroupRepository.save(pinGroup);
    }

    public void deletePin(String pinGroupId, String pinId) {
        PinGroup pinGroup = pinGroupRepository.findOne(pinGroupId)
            .orElseThrow(() -> new PinGroupCannotFoundException("can not find pin group " + pinGroupId));

        pinGroup.deletePin(pinId);

        pinGroupRepository.save(pinGroup);
    }

    @Subscribe
    public void onMessageEvent(PinDeletedEvent event) {
        List<PinGroup> pinGroups = pinGroupRepository.findAll()
            .stream()
            .filter(it -> it.hasPin(event.pinId()))
            .collect(Collectors.toList());

        pinGroups.stream().peek(it -> it.deletePin(event.pinId()));

        pinGroupRepository.saveBatch(pinGroups);
    }
}
