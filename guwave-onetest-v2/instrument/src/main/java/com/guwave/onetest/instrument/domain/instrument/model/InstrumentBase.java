package com.guwave.onetest.instrument.domain.instrument.model;

import com.guwave.onetest.common.util.UUIDUtil;
import com.guwave.onetest.common.domain.Entity;
import lombok.Getter;

@Getter
public class InstrumentBase extends Entity<String> {
    private String name;
    private final InstrumentType type;

    public InstrumentBase(String name, InstrumentType type) {
        super(UUIDUtil.uuid());
        rename(name);
        this.type = type;
    }

    public void rename(String newName) {
        this.name = newName;
    }
}
