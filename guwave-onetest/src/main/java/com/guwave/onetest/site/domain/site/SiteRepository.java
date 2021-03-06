package com.guwave.onetest.site.domain.site;

import com.guwave.onetest.site.domain.pin.model.SiteId;

import java.util.List;
import java.util.Optional;

public interface SiteRepository {
    <IDType> void deleteSite(IDType id);

    void save(Site site);
    <IDType> Optional<Site> findOne(IDType id);

    List<Site> findAll();

    List<Site> findBatch(List<SiteId> siteIds);

    void saveBatch(List<Site> sites);
}
