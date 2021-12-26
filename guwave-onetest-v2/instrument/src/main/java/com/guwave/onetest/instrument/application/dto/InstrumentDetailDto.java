package com.guwave.onetest.instrument.application.dto;

import com.guwave.onetest.instrument.constant.ErrorCode;
import com.guwave.onetest.instrument.domain.instrument.model.DCPowerInstrument;
import com.guwave.onetest.instrument.domain.instrument.model.InstrumentBase;
import com.guwave.onetest.instrument.domain.instrument.model.InstrumentType;
import com.guwave.onetest.instrument.domain.instrument.model.RFPMInstrument;
import com.guwave.onetest.instrument.exception.ChannelNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class InstrumentDetailDto {
    private final InstrumentDetailBaseDto instrumentDetail;

    public static InstrumentDetailDto from(InstrumentBase instrumentBase) {
        return new InstrumentDetailDto(innerFrom(instrumentBase));
    }

    public void checkChannel(Integer channel) {
        // FIXME: 根据实际的业务需求实现，这里只是示例
        if (instrumentDetail.type == InstrumentType.DCPOWER) {
            DCPowerInstrumentDetailDto dcPowerInstrumentDto = (DCPowerInstrumentDetailDto) instrumentDetail;
            if (channel < dcPowerInstrumentDto.numberOfChannel) {
                return;
            }
        }

        throw new ChannelNotFoundException(ErrorCode.CHANNEL_CANNOT_FOUND, "Cannot find channel " + channel + " for instrument " + instrumentDetail.id);
    }

    private static InstrumentDetailBaseDto innerFrom(InstrumentBase instrumentBase) {
        switch (instrumentBase.getType()) {
            case RFRM:
                RFPMInstrument rfpmInstrument = (RFPMInstrument) instrumentBase;
                return new RFPMInstrumentDetailDto(
                    rfpmInstrument.id(),
                    rfpmInstrument.getName(),
                    rfpmInstrument.getCustomFPGAFilePath(),
                    rfpmInstrument.getCalibrationFilePath(),
                    rfpmInstrument.getIviSwitchResourceName(),
                    rfpmInstrument.getPorts());
            case DCPOWER:
                DCPowerInstrument dcPowerInstrument = (DCPowerInstrument) instrumentBase;
                return new DCPowerInstrumentDetailDto(
                    instrumentBase.id(),
                    dcPowerInstrument.getName(),
                    dcPowerInstrument.getNumberOfChannel());
            default:
                return null;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static abstract class InstrumentDetailBaseDto {
        private final String id;
        private final String name;
        private final InstrumentType type;
    }

    @Getter
    public static class DCPowerInstrumentDetailDto extends InstrumentDetailBaseDto{
        private final Integer numberOfChannel;

        public DCPowerInstrumentDetailDto(String id, String name, Integer numberOfChannel) {
            super(id, name, InstrumentType.DCPOWER);
            this.numberOfChannel = numberOfChannel;
        }
    }

    @Getter
    @Setter
    public static class RFPMInstrumentDetailDto extends InstrumentDetailBaseDto{
        private final String customFPGAFilePath;
        private final String calibrationFilePath;
        private final String iviSwitchResourceName;
        private final Set<String> ports;

        public RFPMInstrumentDetailDto(String id, String name, String customFPGAFilePath, String calibrationFilePath, String iviSwitchResourceName, Set<String> ports) {
            super(id, name, InstrumentType.RFRM);
            this.customFPGAFilePath = customFPGAFilePath;
            this.calibrationFilePath = calibrationFilePath;
            this.iviSwitchResourceName = iviSwitchResourceName;
            this.ports = ports;
        }
    }
}


