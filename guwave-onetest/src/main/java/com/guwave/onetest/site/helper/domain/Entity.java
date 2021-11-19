package com.guwave.onetest.site.helper.domain;

import java.util.Objects;

public abstract class Entity<IDType> {
    private final IDType id;

    public Entity(IDType id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("entity id can not be null");
        }
        this.id = id;
    }

    public IDType id() {
        return this.id;
    }

    public boolean sameIdentityAs(Entity<?> other) {
        if (other == null) {
            return false;
        }

        return Objects.equals(id, other.id);
    }
}
