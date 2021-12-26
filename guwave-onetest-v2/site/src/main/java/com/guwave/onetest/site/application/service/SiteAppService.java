package com.guwave.onetest.site.application.service;

import com.guwave.onetest.site.application.dto.AddConnectionDto;
import com.guwave.onetest.site.application.dto.SiteDetailInfoDto;
import com.guwave.onetest.site.application.dto.SiteSimpleInfoDto;
import com.guwave.onetest.site.constant.ErrorCode;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.site.model.Connection;
import com.guwave.onetest.site.domain.site.model.Site;
import com.guwave.onetest.site.domain.site.repository.SiteRepository;
import com.guwave.onetest.site.exception.PinCannotFoundException;
import com.guwave.onetest.site.exception.SiteCannotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteAppService {
    private final SiteRepository siteRepository;
    private final PinRepository pinRepository;

    public String createSite() {
        long count = siteRepository.count();
        Site site = Site.create(count);
        siteRepository.save(site);
        return site.id();
    }

    public void deleteSite(String id) {
        Optional<Site> site = siteRepository.findOne(id);

        if (site.isEmpty()) {
            return;
        }

        siteRepository.deleteById(id);

        List<Site> sites = siteRepository.findAllBySiteNumberLargeThan(site.get().number());
        if (CollectionUtils.isEmpty(sites)) {
            return;
        }

        sites.forEach(it ->
            it.resetNumber(it.number() - 1L)
        );

        siteRepository.saveBatch(sites);
    }

    public String addConnection(AddConnectionDto dto) {
        Site site = findSiteOrThrowException(dto.getSiteId());

        checkPin(dto.getPinId());

        Connection connection = new Connection(dto.getPinId(), dto.getInstrumentId(), dto.getChannel());
        site.addConnection(connection);

        siteRepository.save(site);

        return connection.id();
    }

    public void deleteConnection(String siteId, String connectionId) {
        Optional<Site> site = siteRepository.findOne(siteId);
        if (site.isEmpty()) {
            return;
        }

        site.get().removeConnection(connectionId);

        siteRepository.save(site.get());
    }

    public SiteDetailInfoDto getSiteDetail(String siteId) {
        Site site = findSiteOrThrowException(siteId);

        return SiteDetailInfoDto.from(site);
    }

    public List<SiteSimpleInfoDto> getAllSites() {
        List<Site> sites = siteRepository.findAll();

        return sites.stream().map(SiteSimpleInfoDto::from).collect(Collectors.toList());
    }

    private Site findSiteOrThrowException(String id) {
        return siteRepository.findOne(id).orElseThrow(() ->
            new SiteCannotFoundException(ErrorCode.SITE_CAN_NOT_FOUND, "Can not find site " + id));
    }

    private void checkPin(String pinId) {
        pinRepository.findOne(pinId).orElseThrow(() ->
            new PinCannotFoundException(ErrorCode.PIN_CAN_NOT_FOUND, "Can not find pin " + pinId));
    }
}
