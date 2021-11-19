package com.guwave.onetest.site.helper.domain;

public abstract class ValueObject<T> {
    public abstract boolean sameValueAs(T other);
}
