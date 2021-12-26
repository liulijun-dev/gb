package com.guwave.onetest.site.domain.pin.model;

import com.guwave.onetest.site.domain.pin.event.SiteDeletedEvent;
import com.guwave.onetest.site.helper.domain.Entity;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2(topic = "Pin")
@Getter
public class Pin extends Entity<String> {
    private String name;
    private final List<SiteId> siteIds = new ArrayList<>();

    public static Pin create(String id, String name) {
        return new Pin(id, name);
    }

    public Pin(String id, String name) {
        super(id);
        setName(name);
    }

    public Pin(String id, String name, List<SiteId> siteIds) {
        this(id, name);
        this.siteIds.addAll(siteIds);
    }

    public void renamePin(String newName) {
        setName(newName);
    }

    public void configRelationship(SiteId siteId) {
        if (Objects.isNull(siteId)) {
            throw new IllegalArgumentException("SiteId can not be null");
        }

        if (!hasSiteId(siteId)) {
            this.siteIds.add(siteId);
        }
    }

    /**
     * 删除pin与site间的关系
     * @param siteId siteId
     * @return true 如果删除成功
     */
    public boolean deleteRelationship(SiteId siteId) {
        return this.siteIds.removeIf(it -> it.sameValueAs(siteId));
    }

    public List<SiteId> sites() {
        return this.siteIds;
    }

    private void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Pin name can not be blank");
        }
        this.name = name;
    }

    private boolean hasSiteId(SiteId siteId) {
        return this.siteIds.stream().anyMatch(it -> it.sameValueAs(siteId));
    }
}
