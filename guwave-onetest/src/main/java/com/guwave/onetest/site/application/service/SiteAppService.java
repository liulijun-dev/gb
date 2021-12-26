package com.guwave.onetest.site.application.service;

import com.google.common.eventbus.Subscribe;
import com.guwave.onetest.site.domain.pin.event.PinDeletedEvent;
import com.guwave.onetest.site.domain.site.Site;
import com.guwave.onetest.site.domain.site.SiteRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SiteAppService {
    private final SiteRepository siteRepository;

    public SiteAppService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Subscribe
    public void onMessageEvent(PinDeletedEvent event) {
        List<Site> deletedPinSites = siteRepository.findAll()
            .stream()
            .filter(it -> it.hasPin(event.pinId()))
            .peek(it -> it.deletePin(event.pinId()))
            .collect(Collectors.toList());

        siteRepository.saveBatch(deletedPinSites);
    }
}
