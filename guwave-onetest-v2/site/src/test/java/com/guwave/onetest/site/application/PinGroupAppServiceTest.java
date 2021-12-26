package com.guwave.onetest.site.application;

import com.guwave.onetest.site.application.service.PinGroupAppService;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PinGroupAppServiceTest {
    private PinGroupAppService pinGroupAppService;
    private final PinRepository pinRepository = mock(PinRepository.class);
    private final PinGroupRepository pinGroupRepository = mock(PinGroupRepository.class);

    @BeforeEach
    public void setup() {
        pinGroupAppService = new PinGroupAppService(pinGroupRepository, pinRepository);
    }

    // bypass other test
}
