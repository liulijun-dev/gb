package com.guwave.onetest.site.application;

import com.guwave.onetest.site.application.service.PinAppService;
import com.guwave.onetest.site.application.service.SyncEventBus;
import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.event.PinDeletedEvent;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pin.model.SiteId;
import com.guwave.onetest.site.domain.pingroup.model.PinGroup;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import com.guwave.onetest.site.domain.site.SiteRepository;
import com.guwave.onetest.site.exception.PinCannotFoundException;
import com.guwave.onetest.site.helper.UUIDUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
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
    private final SyncEventBus syncEventBus = mock(SyncEventBus.class);

    @BeforeEach
    public void setup() {
        pinAppService = new PinAppService(pinRepository, pinGroupRepository, siteRepository, syncEventBus);
    }

    @Test
    public void should_success_when_create_pin_given_valid_info() {
        String pinName = "pin";

        pinAppService.createPin(pinName);

        verify(pinRepository, times(1)).save(any());
    }

    @Test
    public void should_throw_exception_when_delete_pin_given_pin_not_exist() {
        String pinId = UUIDUtil.uuid();
        when(pinRepository.findOne(pinId)).thenReturn(Optional.empty());

        assertThrows(PinCannotFoundException.class, () -> pinAppService.deletePin_v1(pinId));
    }

    @Test
    public void should_delete_pin_from_site_when_delete_pin_given_pin_exist() {
        String pinId = UUIDUtil.uuid();
        Pin pin = Pin.create(pinId, "pin");
        pin.configRelationship(new SiteId(UUIDUtil.uuid()));
        PinGroup pinGroup = new PinGroup(UUIDUtil.uuid(), "pingroup");
        pinGroup.addPin(pinId);
        when(pinRepository.findOne(pinId)).thenReturn(Optional.of(pin));
        when(pinGroupRepository.findAll()).thenReturn(List.of(pinGroup));

        pinAppService.deletePin_v1(pinId);

        verify(pinGroupRepository, times(1)).saveBatch(List.of(pinGroup));
        // 验证中间处理过程
        assertEquals(0, pinGroup.pins().size());
    }

    @Test
    public void should_publish_event_when_delete_pin_given_pin_exist() {
        String pinId = UUIDUtil.uuid();
        Pin pin = Pin.create(pinId, "pin");
        when(pinRepository.findOne(pinId)).thenReturn(Optional.of(pin));

        pinAppService.deletePin_v2(pinId);

        // 验证SUT中间生成的数据
        verify(syncEventBus, times(1))
            .post(
                argThat(
                    argument -> ((PinDeletedEvent)argument).pinId().equals(pinId)
                )
            );
    }
}
