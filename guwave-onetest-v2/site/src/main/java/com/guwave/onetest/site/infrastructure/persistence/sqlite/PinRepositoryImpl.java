package com.guwave.onetest.site.infrastructure.persistence.sqlite;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class PinRepositoryImpl implements PinRepository {
    private final LoadingCache<String, Pin> cache = CacheBuilder.newBuilder().build(new CacheLoader<String, Pin>() {
        @Override
        public Pin load(String key) {
            return null;
        }
    });

    @Override
    public void delete(String id) {
        cache.invalidate(id);
    }

    @Override
    public void save(Pin pin) {
        cache.put(pin.id(), pin);
    }

    @Override
    public Optional<Pin> findOne(String id) {
        return Optional.ofNullable(cache.getIfPresent(id));
    }

    @Override
    public List<Pin> findAll() {
        return Collections.unmodifiableList(Lists.newArrayList(cache.asMap().values()));
    }

    @Override
    public Optional<Pin> findByName(String name) {
        return cache.asMap().values().stream().filter(it -> it.getName().equals(name)).findFirst();
    }

    @Override
    public List<Pin> findBatch(Set<String> ids) {
        return Collections.unmodifiableList(Lists.newArrayList(cache.getAllPresent(ids).values()));
    }
}
