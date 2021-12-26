package com.guwave.onetest.site.application.service;

import com.guwave.onetest.site.constant.ErrorCode;
import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pingroup.model.PinGroup;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import com.guwave.onetest.site.domain.site.model.Site;
import com.guwave.onetest.site.domain.site.repository.SiteRepository;
import com.guwave.onetest.site.exception.PinCannotFoundException;
import com.guwave.onetest.site.exception.PinNameRepeatException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PinAppService {
    private final PinRepository pinRepository;
    private final PinGroupRepository pinGroupRepository;
    private final SiteRepository siteRepository;

    public String createPin(String name) {
        Pin pin = Pin.create(name);
        pinRepository.save(pin);
        return pin.id();
    }

    /**
     * 1. 同时操作两个数据源时，需要注意事务
     * 2. 在application service编排多个聚合根
     */
    public void deletePin(@NonNull String id) {
        // delete pin
        Pin pin = pinRepository.findOne(id).orElse(null);
        if (Objects.isNull(pin)) {
            return;
        }

        pinRepository.delete(id);

        // delete pin from group
        List<PinGroup> deletedPinPinGroup = pinGroupRepository.findAllByPinId(pin.id())
            .stream()
            .peek(it -> it.deletePin(id))
            .collect(Collectors.toList());
        pinGroupRepository.saveBatch(deletedPinPinGroup);

        // delete pin from site
        List<Site> deletedPinSites = siteRepository.findAllByPinId(pin.id())
            .stream()
            .peek(it -> it.removeConnectionByPinId(id))
            .collect(Collectors.toList());
        siteRepository.saveBatch(deletedPinSites);
    }

    public void renamePin(String id, String newName) {
        Pin pin = findPinOrThrowException(id);

        Optional<Pin> pinHasSameNewName = pinRepository.findByName(newName);
        if (pinHasSameNewName.isPresent() && !pinHasSameNewName.get().id().equals(id)) {
            throw new PinNameRepeatException(
                ErrorCode.PIN_NAME_REPEAT,
                pinHasSameNewName.get().id() + " has the same new name");
        }

        pin.rename(newName);
        pinRepository.save(pin);
    }

    private Pin findPinOrThrowException(String id) {
        return pinRepository.findOne(id).orElseThrow(() ->
            new PinCannotFoundException(ErrorCode.PIN_CAN_NOT_FOUND, "can not find pin " + id));
    }
}
