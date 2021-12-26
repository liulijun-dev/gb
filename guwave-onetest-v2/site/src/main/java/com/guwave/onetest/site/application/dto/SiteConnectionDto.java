package com.guwave.onetest.site.application.dto;

import com.guwave.onetest.site.domain.site.model.Connection;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SiteConnectionDto {
    private final String id;
    private final String pinId;
    private final String instrumentId;
    private final int channelNumber;

    public static SiteConnectionDto from(Connection connection) {
        return new SiteConnectionDto(connection.id(), connection.getPinId(), connection.getInstrumentId(), connection.getChannelNumber());
    }
}
