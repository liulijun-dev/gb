package com.guwave.onetest.site.application.service;

import com.guwave.onetest.site.constant.ErrorCode;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pingroup.model.PinGroup;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import com.guwave.onetest.site.exception.PinCannotFoundException;
import com.guwave.onetest.site.exception.PinGroupCannotFoundException;
import com.guwave.onetest.site.exception.PinGroupNameRepeatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PinGroupAppService {
    private final PinGroupRepository pinGroupRepository;
    private final PinRepository pinRepository;

    public String createPinGroup(String name) {
        PinGroup pinGroup = PinGroup.create(name);
        pinGroupRepository.save(pinGroup);
        return pinGroup.id();
    }

    public void deletePinGroup(String id) {
        pinGroupRepository.delete(id);
    }

    public void renamePinGroup(String id, String newName) {
        PinGroup pinGroup = findPinGroupOrThrowException(id);

        Optional<PinGroup> sameNamePinGroup = pinGroupRepository.findByName(newName);
        if (sameNamePinGroup.isPresent() && !sameNamePinGroup.get().id().equals(id)) {
            throw new PinGroupNameRepeatException(
                ErrorCode.PIN_GROUP_NAME_REPEAT,
                sameNamePinGroup.get().id() + "has the same new name"
            );
        }

        pinGroup.rename(newName);

        pinGroupRepository.save(pinGroup);
    }

    public void addPin(String pinGroupId, String pinId) {
        PinGroup pinGroup = findPinGroupOrThrowException(pinGroupId);

        pinRepository.findOne(pinId).orElseThrow(() ->
            new PinCannotFoundException(ErrorCode.PIN_CAN_NOT_FOUND, "can not find pin " + pinId));

        pinGroup.addPin(pinId);

        pinGroupRepository.save(pinGroup);
    }

    public void deletePin(String pinGroupId, String pinId) {
        PinGroup pinGroup = findPinGroupOrThrowException(pinGroupId);

        pinGroup.deletePin(pinId);

        pinGroupRepository.save(pinGroup);
    }

    private PinGroup findPinGroupOrThrowException(String id) {
        return pinGroupRepository.findOne(id).orElseThrow(() ->
            new PinGroupCannotFoundException(ErrorCode.PIN_GROUP_NOT_FOUND, "can not find pin group " + id));
    }
}
