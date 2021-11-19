package com.guwave.onetest.site;

import com.guwave.onetest.site.application.service.PinGroupAppService;
import com.guwave.onetest.site.application.service.SiteAppService;
import com.guwave.onetest.site.application.service.SyncEventBus;
import com.guwave.onetest.site.infrastructure.message.SyncEventBusImpl;
import com.guwave.onetest.site.infrastructure.persistence.xml.PinGroupRepositoryXml;
import com.guwave.onetest.site.infrastructure.persistence.xml.PinRepositoryXml;
import com.guwave.onetest.site.infrastructure.persistence.xml.SiteRepositoryXml;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuwaveOnetestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuwaveOnetestApplication.class, args);
        initSyncEventBus();
    }

    public static void initSyncEventBus() {
        SyncEventBus syncEventBus = SyncEventBusImpl.getInstance();
        PinGroupAppService pinGroupAppService = new PinGroupAppService(new PinGroupRepositoryXml(), new PinRepositoryXml());
        SiteAppService siteAppService = new SiteAppService(new SiteRepositoryXml());
        syncEventBus.register(pinGroupAppService);
        syncEventBus.register(siteAppService);
    }
}
