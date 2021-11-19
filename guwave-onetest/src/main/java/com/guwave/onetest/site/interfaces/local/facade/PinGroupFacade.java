package com.guwave.onetest.site.interfaces.local.facade;

import com.guwave.onetest.site.interfaces.local.dto.PinGroupDetailDto;

public interface PinGroupFacade {
    PinGroupDetailDto pinGroupDetail(String pinGroupId);

    String createPinGroup(String name);
}
