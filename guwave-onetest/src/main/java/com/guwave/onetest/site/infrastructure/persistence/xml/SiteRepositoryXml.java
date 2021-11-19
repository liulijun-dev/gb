package com.guwave.onetest.site.infrastructure.persistence.xml;

import com.guwave.onetest.site.domain.pin.model.SiteId;
import com.guwave.onetest.site.domain.site.Site;
import com.guwave.onetest.site.domain.site.SiteRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Optional;

public class SiteRepositoryXml implements SiteRepository {

    @Override
    public <IDType> void deleteSite(IDType id) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public void save(Site site) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public <IDType> Optional<Site> findOne(IDType id) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public List<Site> findAll() {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public List<Site> findAll(List<SiteId> siteIds) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public void saveAll(List<Site> sites) {
        throw new NotImplementedException("not implemented");
    }
}
