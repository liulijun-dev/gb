package com.guwave.onetest.site.domain.site;

import com.guwave.onetest.site.helper.domain.Entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Site extends Entity<String> {
    private boolean isActive = false;
    private Set<String> pinIds;
    private List<Instrument> instruments;

    public static Site create(String id) {
        return new Site(id);
    }

    public Site(String id) {
        super(id);
    }

    public boolean isActive() {
        return isActive;
    }

    public Set<String> pins() {
        return pinIds;
    }

    public void deletePin(String pinId) {
        if (!Objects.isNull(pinId)) {
            this.pinIds.remove(pinId);
        }
    }

    public boolean hasPin(String pinId) {
        return pinIds.contains(pinId);
    }
}
