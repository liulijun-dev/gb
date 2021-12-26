package com.guwave.onetest.instrument.domain.instrument.model;

import lombok.Getter;

@Getter
public class DCPowerInstrument extends InstrumentBase {
    private int numberOfChannel;

    public DCPowerInstrument(String name, int numberOfChannel) {
        super(name, InstrumentType.DCPOWER);
        setNumberOfChannel(numberOfChannel);
    }

    public void setNumberOfChannel(int numberOfChannel) {
        this.numberOfChannel = numberOfChannel;
    }
}
