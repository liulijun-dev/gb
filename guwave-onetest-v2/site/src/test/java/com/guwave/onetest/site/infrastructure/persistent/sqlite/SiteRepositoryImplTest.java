package com.guwave.onetest.site.infrastructure.persistent.sqlite;

import com.guwave.onetest.site.domain.site.model.Site;
import com.guwave.onetest.site.domain.site.repository.SiteRepository;
import com.guwave.onetest.site.infrastructure.persistence.sqlite.SiteRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteRepositoryImplTest {
    private SiteRepository siteRepository;

    @BeforeEach
    public void setup() {
        this.siteRepository = new SiteRepositoryImpl();
    }

    @Test
    public void should_success_when_save_site_given_site() {
        Site site = Site.create(1);

        siteRepository.save(site);

        Optional<Site> savedSite = siteRepository.findOne(site.id());
        assertTrue(savedSite.isPresent());
        assertEquals(site.number(), savedSite.get().number());
    }

    @Test
    public void should_success_when_delete_site_given_site_exist() {
        Site site = Site.create(1);
        siteRepository.save(site);

        siteRepository.deleteById(site.id());

        assertEquals(0, siteRepository.findAll().size());
    }

    // by pass other unit test
}
