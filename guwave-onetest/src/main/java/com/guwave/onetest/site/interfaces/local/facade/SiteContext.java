package com.guwave.onetest.site.interfaces.local.facade;

import com.guwave.onetest.site.application.service.PinGroupAppService;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import com.guwave.onetest.site.infrastructure.persistence.xml.PinGroupRepositoryXml;
import com.guwave.onetest.site.infrastructure.persistence.xml.PinRepositoryXml;
import com.guwave.onetest.site.interfaces.local.facade.impl.PinFacadeImpl;
import com.guwave.onetest.site.interfaces.local.facade.impl.PinGroupFacadeImpl;

public final class SiteContext {
    public static PinFacade pinFacade() {
        return new PinFacadeImpl();
    }

    public static PinGroupFacade pinGroupFacade() {
        PinRepository pinRepository = new PinRepositoryXml();
        PinGroupRepository pinGroupRepository = new PinGroupRepositoryXml();
        PinGroupAppService pinGroupAppService = new PinGroupAppService(pinGroupRepository, pinRepository);

        return new PinGroupFacadeImpl(pinRepository, pinGroupRepository, pinGroupAppService);
    }
}
