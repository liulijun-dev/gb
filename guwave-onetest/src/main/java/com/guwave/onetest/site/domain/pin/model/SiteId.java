package com.guwave.onetest.site.domain.pin.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.guwave.onetest.site.helper.domain.ValueObject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
public class SiteId extends ValueObject<SiteId> {
    private final String siteId;

    @JsonCreator
    public SiteId(@JsonProperty(value = "siteId", required = true) String siteId) {
        if (StringUtils.isBlank(siteId)) {
            throw new IllegalArgumentException("site id can not be blank");
        }
        this.siteId = siteId;
    }

    public String siteId() {
        return siteId;
    }

    @Override
    public boolean sameValueAs(SiteId other) {
        if (Objects.isNull(other)) {
            return false;
        }

        return siteId.equals(other.siteId);
    }
}
