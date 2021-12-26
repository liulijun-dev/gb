package com.guwave.onetest.site.domain.site.model;

import com.guwave.onetest.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Instrument extends ValueObject<Instrument> {
    private final String instrumentId;
    private final Set<String> channelIds;

    public Instrument(String instrumentId, Set<String> channelIds) {
        if (StringUtils.isBlank(instrumentId)) {
            throw new IllegalArgumentException("instrument id can not be blank");
        }
        this.instrumentId = instrumentId;
        this.channelIds = channelIds == null ? new HashSet<>() : channelIds;
    }

    @Override
    public boolean sameValueAs(Instrument other) {
        if (Objects.isNull(other)) {
            return false;
        }

        return instrumentId.equals(other.instrumentId) &&
            channelIds.size() == other.channelIds.size() &&
            other.channelIds.containsAll(channelIds);
    }
}
