package com.guwave.onetest.bff.interfaces.local.facade;

import com.guwave.onetest.bff.interfaces.local.facade.impl.PinFacadeImpl;
import com.guwave.onetest.bff.interfaces.local.facade.impl.PinGroupFacadeImpl;
import com.guwave.onetest.site.application.service.PinGroupAppService;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import com.guwave.onetest.site.infrastructure.persistence.sqlite.PinGroupRepositoryImpl;
import com.guwave.onetest.site.infrastructure.persistence.sqlite.PinRepositoryImpl;

public final class SiteContext {
    public static PinFacade pinFacade() {
        return new PinFacadeImpl();
    }

    public static PinGroupFacade pinGroupFacade() {
        PinRepository pinRepository = new PinRepositoryImpl();
        PinGroupRepository pinGroupRepository = new PinGroupRepositoryImpl();
        PinGroupAppService pinGroupAppService = new PinGroupAppService(pinGroupRepository, pinRepository);

        return new PinGroupFacadeImpl(pinRepository, pinGroupRepository, pinGroupAppService);
    }
}
