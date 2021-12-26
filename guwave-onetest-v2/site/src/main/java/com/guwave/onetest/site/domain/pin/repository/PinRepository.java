package com.guwave.onetest.site.domain.pin.repository;

import com.guwave.onetest.site.domain.pin.model.Pin;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PinRepository {
    void delete(String id);

    void save(Pin pin);

    Optional<Pin> findOne(String id);

    List<Pin> findAll();

    Optional<Pin> findByName(String name);

    List<Pin> findBatch(Set<String> ids);
}
