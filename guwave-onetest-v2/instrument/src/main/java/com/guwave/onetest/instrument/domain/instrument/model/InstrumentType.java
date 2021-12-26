package com.guwave.onetest.instrument.domain.instrument.model;

public enum InstrumentType {
    DCPOWER("DCPower"),
    DIGITAL_PATTERN("Digital Pattern"),
    RFRM("RFRM");

    private String readableName;

    InstrumentType(String name) {
        this.readableName = name;
    }
}
