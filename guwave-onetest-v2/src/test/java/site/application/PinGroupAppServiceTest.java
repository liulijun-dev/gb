package site.application;

import com.guwave.onetest.site.application.service.PinGroupAppService;
import com.guwave.onetest.site.application.service.SyncEventBus;
import com.guwave.onetest.site.domain.pin.event.PinDeletedEvent;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import com.guwave.onetest.site.helper.UUIDUtil;
import com.guwave.onetest.site.infrastructure.message.SyncEventBusImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PinGroupAppServiceTest {
    private PinGroupAppService pinGroupAppService;
    private final PinRepository pinRepository = mock(PinRepository.class);
    private final PinGroupRepository pinGroupRepository = mock(PinGroupRepository.class);
    private final SyncEventBus syncEventBus = SyncEventBusImpl.getInstance();

    @BeforeEach
    public void setup() {
        pinGroupAppService = new PinGroupAppService(pinGroupRepository, pinRepository);
        syncEventBus.register(pinGroupAppService);
    }

    @AfterEach
    public void destroy() {
        syncEventBus.unregister(pinGroupAppService);
    }

    @Test
    public void should_receive_event_when_delete_pin_success() {
        pinGroupAppService = mock(PinGroupAppService.class);
        syncEventBus.register(pinGroupAppService);

        syncEventBus.post(new PinDeletedEvent(UUIDUtil.uuid()));

        // 由于event bus的异步性，这个并不是一个稳定的测试，这也体现了异步消息的难测性，可以通过E2E测试或集成测试进行测试覆盖
        verify(pinGroupAppService, times(1)).onMessageEvent(any());
        syncEventBus.register(pinGroupAppService);
    }
}
