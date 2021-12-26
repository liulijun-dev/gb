package com.guwave.onetest.bff.interfaces.local.facade;

import com.guwave.onetest.bff.interfaces.local.dto.PinGroupDetailDto;

public interface PinGroupFacade {
    PinGroupDetailDto pinGroupDetail(String pinGroupId);

    String createPinGroup(String name);
}
