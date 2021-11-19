package com.guwave.onetest.site.interfaces.local.facade.impl;

import com.guwave.onetest.site.application.service.PinGroupAppService;
import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pingroup.model.PinGroup;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import com.guwave.onetest.site.exception.PinCannotFoundException;
import com.guwave.onetest.site.interfaces.local.dto.PinDetailDto;
import com.guwave.onetest.site.interfaces.local.dto.PinGroupDetailDto;
import com.guwave.onetest.site.interfaces.local.facade.PinGroupFacade;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PinGroupFacadeImpl implements PinGroupFacade {
    private final PinRepository pinRepository;
    private final PinGroupRepository pinGroupRepository;
    private final PinGroupAppService pinGroupAppService;

    @Override
    public PinGroupDetailDto pinGroupDetail(String pinGroupId) {
        PinGroup pinGroup =
            pinGroupRepository.findOne(pinGroupId).orElseThrow(() -> new PinCannotFoundException("Cannot find pin group " + pinGroupId));
        List<Pin> pins = pinRepository.findBatch(pinGroup.pins());

        List<PinDetailDto> pinDetails = new ArrayList<>();
        for (Pin pin : pins) {
            pinDetails.add(new PinDetailDto(pin.id(), pin.getName()));
        }
        PinGroupDetailDto pinGroupDetail = new PinGroupDetailDto(pinGroup.id(),pinGroup.name(),pinDetails);

        return pinGroupDetail;
    }

    @Override
    public String createPinGroup(String name) {
        return pinGroupAppService.createPinGroup(name);
    }
}
