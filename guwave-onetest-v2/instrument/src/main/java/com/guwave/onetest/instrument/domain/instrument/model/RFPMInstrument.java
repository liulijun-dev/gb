package com.guwave.onetest.instrument.domain.instrument.model;

import java.util.Set;

public class RFPMInstrument extends InstrumentBase{
    private String customFPGAFilePath;
    private String calibrationFilePath;
    private String iviSwitchResourceName;
    private Set<String> ports;

    public RFPMInstrument(String name) {
        super(name, InstrumentType.RFRM);
    }

    //省略业务函数
}
