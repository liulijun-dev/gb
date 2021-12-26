package com.guwave.onetest.common.domain;

public abstract class ValueObject<T> {
    public abstract boolean sameValueAs(T other);
}
