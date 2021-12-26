package com.guwave.onetest.site.domain.site.repository;

import com.guwave.onetest.site.domain.site.model.Site;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SiteRepository {
    Optional<Site> findOne(String id);

    void deleteById(String id);

    void save(Site site);

    List<Site> findAll();

    List<Site> findBatch(Set<String> ids);

    void saveBatch(List<Site> sites);

    List<Site> findAllByPinId(String id);

    long count();

    List<Site> findAllBySiteNumberLargeThan(long number);
}
