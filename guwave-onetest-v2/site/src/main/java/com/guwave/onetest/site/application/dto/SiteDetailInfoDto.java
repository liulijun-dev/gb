package com.guwave.onetest.site.application.dto;

import com.guwave.onetest.site.domain.site.model.Site;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class SiteDetailInfoDto {
    private final String id;
    private final Long number;
    private final List<SiteConnectionDto> connections;

    public static SiteDetailInfoDto from(Site site) {
        List<SiteConnectionDto> connections =
            site.connections().stream().map(SiteConnectionDto::from).collect(Collectors.toList());

        return new SiteDetailInfoDto(site.id(), site.number(), connections);
    }
}
