package com.guwave.onetest.site.domain.pingroup.model;

import com.guwave.onetest.site.helper.domain.Entity;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class PinGroup extends Entity<String> {
    private String name;
    private final Set<String> pins = new HashSet<>();

    public static PinGroup create(String id, String name) {
        return new PinGroup(id, name);
    }
    public PinGroup(String id, String name) {
        super(id);
        setName(name);
    }

    public void renamePinGroup(String newName) {
        setName(newName);
    }

    public void addPin(String pinId) {
        if (StringUtils.isNoneBlank(pinId)) {
            this.pins.add(pinId);
        }
    }

    public void deletePin(String pidId) {
        if (StringUtils.isNoneBlank()) {
            this.pins.remove(pidId);
        }
    }

    public String name() {
        return this.name;
    }

    public Set<String> pins() {
        return this.pins;
    }

    public boolean hasPin(String pinId) {
        return pins.contains(pinId);
    }

    private void setName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name can not be blank");
        }
        this.name = name;
    }

}
