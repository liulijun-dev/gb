package com.guwave.onetest.instrument.application.dto;

import com.guwave.onetest.common.exception.UserInputParameterIllegalException;
import com.guwave.onetest.instrument.constant.ErrorCode;
import com.guwave.onetest.instrument.domain.instrument.model.DCPowerInstrument;
import com.guwave.onetest.instrument.domain.instrument.model.InstrumentBase;
import com.guwave.onetest.instrument.domain.instrument.model.InstrumentType;
import com.guwave.onetest.instrument.domain.instrument.model.RFPMInstrument;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
public class CreateInstrumentDto {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotNull(message = "type cannot be null")
    private InstrumentType type;

    private Integer numberOfChannel;
    private String customFPGAFilePath;
    private String calibrationFilePath;
    private String iviSwitchResourceName;
    private Set<String> ports;

    public InstrumentBase toInstrument() {
        switch (type) {
            case DCPOWER:
            return DCPowerInstrument.create(name, numberOfChannel);
            case RFRM:
                    RFPMInstrument rfpmInstrument = new RFPMInstrument(name);
                    rfpmInstrument.setPorts(ports);
                    rfpmInstrument.setCalibrationFilePath(calibrationFilePath);
                    rfpmInstrument.setCustomFPGAFilePath(customFPGAFilePath);
                    rfpmInstrument.setIviSwitchResourceName(iviSwitchResourceName);
                    return rfpmInstrument;
            default:
                throw new UserInputParameterIllegalException(
                    ErrorCode.INSTRUMENT_TYPE_NOT_SUPPORT,
                    "not support instrument type: " + type);
        }
    }
}
