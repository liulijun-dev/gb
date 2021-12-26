package com.guwave.onetest.bff.interfaces;

import com.guwave.onetest.common.BaseResponse;
import com.guwave.onetest.common.dto.IdOnlyDto;
import com.guwave.onetest.instrument.application.InstrumentAppService;
import com.guwave.onetest.instrument.application.dto.CreateInstrumentDto;
import com.guwave.onetest.instrument.application.dto.InstrumentDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/instrument/v1/")
public class InstrumentApi {
    private final InstrumentAppService instrumentAppService;

    @PostMapping("instruments")
    public BaseResponse<IdOnlyDto<String>> createInstrument(@RequestBody @Validated CreateInstrumentDto dto) {
        String instrumentId = instrumentAppService.createInstrument(dto);
        return BaseResponse.success(IdOnlyDto.instance(instrumentId));
    }

    @DeleteMapping("instruments/{instrumentId}")
    public BaseResponse<Void> deleteInstrument(@PathVariable("instrumentId") String instrumentId) {
        instrumentAppService.deleteInstrument(instrumentId);
        return BaseResponse.success();
    }

    @GetMapping("instruments/{instrumentId}")
    public BaseResponse<InstrumentDetailDto> getInstrumentDetail(@PathVariable("instrumentId") String instrumentId) {
        InstrumentDetailDto dto = instrumentAppService.getInstrumentDetail(instrumentId);
        return BaseResponse.success(dto);
    }
}
