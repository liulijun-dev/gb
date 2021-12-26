package com.guwave.onetest.site.application.dto;

import com.guwave.onetest.site.domain.site.model.Site;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SiteSimpleInfoDto {
    private final String id;
    private final Long number;

    public static SiteSimpleInfoDto from(Site site) {
        return new SiteSimpleInfoDto(site.id(), site.number());
    }
}
