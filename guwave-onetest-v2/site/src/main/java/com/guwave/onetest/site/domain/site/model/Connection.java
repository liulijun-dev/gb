package com.guwave.onetest.site.domain.site.model;

import com.guwave.onetest.common.domain.Entity;
import com.guwave.onetest.common.util.UUIDUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Connection extends Entity<String> {
    private String pinId;
    private String instrumentId;
    private int channelNumber;

    public Connection(String pinId, String instrumentId, int channelNumber) {
        super(UUIDUtil.uuid());
        setPinId(pinId);
        setInstrumentId(instrumentId);
        setChannelNumber(channelNumber);
    }
}
