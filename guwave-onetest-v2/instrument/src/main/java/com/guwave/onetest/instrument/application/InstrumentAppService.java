package com.guwave.onetest.instrument.application;

import com.guwave.onetest.instrument.application.dto.CreateInstrumentDto;
import com.guwave.onetest.instrument.application.dto.InstrumentDetailDto;
import com.guwave.onetest.instrument.constant.ErrorCode;
import com.guwave.onetest.instrument.domain.instrument.model.InstrumentBase;
import com.guwave.onetest.instrument.domain.instrument.repository.InstrumentRepository;
import com.guwave.onetest.instrument.exception.InstrumentCannotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstrumentAppService {
    private final InstrumentRepository instrumentRepository;

    public String createInstrument(CreateInstrumentDto dto) {
        InstrumentBase instrument = dto.toInstrument();
        instrumentRepository.save(instrument);
        return instrument.id();
    }

    public void deleteInstrument(String id) {
        instrumentRepository.deleteById(id);
    }

    public InstrumentDetailDto getInstrumentDetail(String id) {
        InstrumentBase instrument = findInstrumentOrThrowException(id);
        return InstrumentDetailDto.from(instrument);
    }

    private InstrumentBase findInstrumentOrThrowException(String id) {
        return instrumentRepository.findOne(id).orElseThrow(() ->
            new InstrumentCannotFoundException(
                ErrorCode.INSTRUMENT_CANNOT_FOUND,
                "Can not find instrument " + id));
    }
}
