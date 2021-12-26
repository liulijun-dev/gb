package com.guwave.onetest.site.domain.pingroup.model;

import com.guwave.onetest.common.domain.Entity;
import com.guwave.onetest.common.util.UUIDUtil;
import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Set;

public class PinGroup extends Entity<String> {
    private String name;
    private final Set<String> pins = new HashSet<>();

    public static PinGroup create(String name) {
        return new PinGroup(UUIDUtil.uuid(), name);
    }
    public PinGroup(String id, String name) {
        super(id);
        setName(name);
    }

    public void rename(String newName) {
        setName(newName);
    }

    public void addPin(@NonNull String pinId) {
        this.pins.add(pinId);
    }

    public void deletePin(@NonNull String pidId) {
        this.pins.remove(pidId);
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
        this.name = name;
    }
}
