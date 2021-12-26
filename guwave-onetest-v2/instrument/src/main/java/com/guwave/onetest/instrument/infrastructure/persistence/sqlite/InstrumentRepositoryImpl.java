package com.guwave.onetest.instrument.infrastructure.persistence.sqlite;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.guwave.onetest.instrument.domain.instrument.model.InstrumentBase;
import com.guwave.onetest.instrument.domain.instrument.repository.InstrumentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InstrumentRepositoryImpl implements InstrumentRepository {
    private final LoadingCache<String, InstrumentBase> cache = CacheBuilder.newBuilder().build(new CacheLoader<>() {
        @Override
        public InstrumentBase load(String key) {
            return null;
        }
    });

    @Override
    public void save(InstrumentBase instrument) {
        cache.put(instrument.id(), instrument);
    }

    @Override
    public Optional<InstrumentBase> findOne(String id) {
        return Optional.ofNullable(cache.getIfPresent(id));
    }

    @Override
    public List<InstrumentBase> findAll() {
        return cache.asMap().values().stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void deleteById(String id) {
        cache.invalidate(id);
    }
}
