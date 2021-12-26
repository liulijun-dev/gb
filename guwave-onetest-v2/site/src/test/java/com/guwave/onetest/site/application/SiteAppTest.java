package com.guwave.onetest.site.application;

import com.guwave.onetest.common.util.UUIDUtil;
import com.guwave.onetest.site.application.dto.AddConnectionDto;
import com.guwave.onetest.site.application.service.SiteAppService;
import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.site.model.Connection;
import com.guwave.onetest.site.domain.site.model.Site;
import com.guwave.onetest.site.domain.site.repository.SiteRepository;
import com.guwave.onetest.site.exception.PinCannotFoundException;
import com.guwave.onetest.site.exception.SiteCannotFoundException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SiteAppTest {
    private SiteAppService siteAppService;
    private final SiteRepository siteRepository = mock(SiteRepository.class);
    private final PinRepository pinRepository = mock(PinRepository.class);


    @BeforeEach
    public void setup() {
        siteAppService = new SiteAppService(siteRepository, pinRepository);
    }

    @Test
    public void should_success_when_create_site_given_no_site() {
        when(siteRepository.count()).thenReturn(0L);

        siteAppService.createSite();

        verify(siteRepository).save(argThat(site -> site.number() == 0));
    }

    @Test
    public void should_success_when_create_site_given_two_sites_exist() {
        when(siteRepository.count()).thenReturn(2L);

        siteAppService.createSite();

        verify(siteRepository).save(argThat(site -> site.number() == 2L));
    }

    @Test
    public void should_success_when_delete_site_given_site_does_not_exist() {
        when(siteRepository.findOne(anyString())).thenReturn(Optional.empty());

        siteAppService.deleteSite(UUIDUtil.uuid());

        verify(siteRepository, times(0)).deleteById(anyString());
    }

    @Test
    public void should_success_when_delete_site_given_site_exists_and_the_site_number_is_the_largest() {
        Site site = Site.create(1);
        when(siteRepository.findOne(anyString())).thenReturn(Optional.of(site));
        when(siteRepository.findAllBySiteNumberLargeThan(site.number())).thenReturn(Collections.emptyList());

        siteAppService.deleteSite(site.id());

        verify(siteRepository, times(1)).deleteById(anyString());
        verify(siteRepository, times(0)).saveBatch(any());
    }

    @Test
    public void should_success_when_delete_site_given_site_exists_and_the_site_number_is_not_the_largest() {
        Site site = Site.create(1);
        Site site2 = Site.create(2);
        Site site3 = Site.create(3);
        when(siteRepository.findOne(anyString())).thenReturn(Optional.of(site));
        when(siteRepository.findAllBySiteNumberLargeThan(site.number())).thenReturn(Lists.newArrayList(site2, site3));

        siteAppService.deleteSite(site.id());

        verify(siteRepository, times(1)).deleteById(anyString());
        verify(siteRepository).saveBatch(argThat(sites -> {
            Site updateSite2 = sites.stream().filter(it -> it.id().equals(site2.id())).findFirst().get();
            Site updateSite3 = sites.stream().filter(it -> it.id().equals(site3.id())).findFirst().get();

            return updateSite2.number() == 1 && updateSite3.number() == 2;
        }));
    }

    @Test
    public void should_success_when_add_connection_given_site_and_pin_exists() {
        Site site = Site.create(1);
        Pin pin = Pin.create("pin");
        when(siteRepository.findOne(anyString())).thenReturn(Optional.of(site));
        when(pinRepository.findOne(anyString())).thenReturn(Optional.of(pin));

        siteAppService.addConnection(new AddConnectionDto(site.id(), pin.id(), "instrument", 0));

        verify(siteRepository, times(1)).save(argThat(it ->
            it.connections().size() == 1 && it.connections().get(0).getPinId().equals(pin.id())));
    }

    @Test
    public void should_throw_exception_when_add_connection_given_site_does_not_exists() {
        when(siteRepository.findOne(anyString())).thenReturn(Optional.empty());

        assertThrows(SiteCannotFoundException.class, () -> siteAppService.addConnection(new AddConnectionDto(UUIDUtil.uuid(), UUIDUtil.uuid(), "instrument", 0)));
    }

    @Test
    public void should_throw_exception_when_add_connection_given_pin_does_not_exists() {
        Site site = Site.create(1);
        when(siteRepository.findOne(anyString())).thenReturn(Optional.of(site));
        when(pinRepository.findOne(anyString())).thenReturn(Optional.empty());

        assertThrows(PinCannotFoundException.class, () -> siteAppService.addConnection(new AddConnectionDto(site.id(), UUIDUtil.uuid(), "instrument", 0)));
    }

    @Test
    public void should_success_when_delete_connection_given_site_does_not_exists() {
        Site site = Site.create(1);
        when(siteRepository.findOne(anyString())).thenReturn(Optional.empty());

        siteAppService.deleteConnection(site.id(), UUIDUtil.uuid());

        verify(siteRepository, times(0)).save(any());
    }

    @Test
    public void should_success_when_delete_connection_given_site_exists() {
        Site site = Site.create(1);
        Connection connection = new Connection(UUIDUtil.uuid(), "instrument", 0);
        site.addConnection(connection);
        when(siteRepository.findOne(anyString())).thenReturn(Optional.of(site));

        siteAppService.deleteConnection(site.id(), connection.id());

        verify(siteRepository, times(1)).save(argThat(it -> it.connections().size() == 0));
    }
}
