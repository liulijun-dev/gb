package com.guwave.onetest.site.application;

import com.google.common.collect.Lists;
import com.guwave.onetest.common.util.UUIDUtil;
import com.guwave.onetest.site.application.service.PinAppService;
import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pingroup.model.PinGroup;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import com.guwave.onetest.site.domain.site.model.Connection;
import com.guwave.onetest.site.domain.site.model.Site;
import com.guwave.onetest.site.domain.site.repository.SiteRepository;
import com.guwave.onetest.site.exception.PinCannotFoundException;
import com.guwave.onetest.site.exception.PinNameRepeatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PinAppServiceTest {
    private PinAppService pinAppService;
    private final PinRepository pinRepository = mock(PinRepository.class);
    private final PinGroupRepository pinGroupRepository = mock(PinGroupRepository.class);
    private final SiteRepository siteRepository = mock(SiteRepository.class);

    @BeforeEach
    public void setup() {
        pinAppService = new PinAppService(pinRepository, pinGroupRepository, siteRepository);
    }

    @Test
    public void should_success_when_create_pin_given_valid_info() {
        String pinName = "pin";

        pinAppService.createPin(pinName);

        verify(pinRepository, times(1)).save(any());
    }

    @Test
    public void should_success_when_delete_pin_given_pin_not_exist() {
        String pinId = UUIDUtil.uuid();
        when(pinRepository.findOne(pinId)).thenReturn(Optional.empty());

        pinAppService.deletePin(pinId);

        verify(pinRepository, times(0)).delete(pinId);
    }

    @Test
    public void should_success_when_delete_pin_given_pin_exist() {
        Pin pin = Pin.create("pin");
        PinGroup pinGroup = PinGroup.create("pingroup");
        pinGroup.addPin(pin.id());
        Site site = Site.create(0);
        Connection connection = new Connection(pin.id(), "testInstrument", 0);
        site.addConnection(connection);
        when(pinRepository.findOne(any())).thenReturn(Optional.of(pin));
        when(pinGroupRepository.findAllByPinId(any())).thenReturn(Lists.newArrayList(pinGroup));
        when(siteRepository.findAllByPinId(any())).thenReturn(Lists.newArrayList(site));

        pinAppService.deletePin(pin.id());

        verify(pinRepository, times(1)).delete(pin.id());
        verify(pinGroupRepository).saveBatch(argThat(pinGroups -> pinGroups.stream().noneMatch(it -> it.hasPin(pin.id()))));
        verify(siteRepository).saveBatch(argThat(sites -> sites.stream().noneMatch(it -> it.hasConnection(connection.id()))));
    }

    @Test
    public void should_throw_exception_when_rename_pin_given_pin_is_not_exist() {
        when(pinRepository.findOne(any())).thenReturn(Optional.empty());

        assertThrows(PinCannotFoundException.class,
            () -> pinAppService.renamePin(UUIDUtil.uuid(), "newName"));
    }

    @Test
    public void should_success_when_rename_pin_given_new_name_is_not_exist() {
        Pin pin = Pin.create("pin");
        String newPinName = "newName";

        when(pinRepository.findOne(any())).thenReturn(Optional.of(pin));
        when(pinRepository.findByName(any())).thenReturn(Optional.empty());

        pinAppService.renamePin(pin.id(), newPinName);

        assertEquals(newPinName, pin.getName());
    }

    @Test
    public void should_throw_exception_when_rename_pin_given_new_name_exist() {
        Pin pin = Pin.create("pin");
        String newPinName = "newName";
        Pin sameNamePin = Pin.create(newPinName);

        when(pinRepository.findOne(any())).thenReturn(Optional.of(pin));
        when(pinRepository.findByName(any())).thenReturn(Optional.of(sameNamePin));

        assertThrows(PinNameRepeatException.class, () -> pinAppService.renamePin(pin.id(), newPinName));
    }
}
