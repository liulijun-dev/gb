package com.guwave.onetest.site.infrastructure.persistence.sqlite;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.guwave.onetest.site.domain.pingroup.model.PinGroup;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PinGroupRepositoryImpl implements PinGroupRepository {
    private final LoadingCache<String, PinGroup> cache = CacheBuilder.newBuilder().build(new CacheLoader<>() {
        @Override
        public PinGroup load(String key) {
            return null;
        }
    });

    @Override
    public void deletePin(String pinGroupId, String pinId) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public void delete(String id) {
        cache.invalidate(id);
    }

    @Override
    public void save(PinGroup pinGroup) {
        cache.put(pinGroup.id(), pinGroup);
    }

    @Override
    public void saveBatch(List<PinGroup> pinGroups) {
        pinGroups.forEach(this::save);
    }

    @Override
    public Optional<PinGroup> findOne(String id) {
        return Optional.ofNullable(cache.getIfPresent(id));
    }

    @Override
    public List<PinGroup> findAll() {
        return Collections.unmodifiableList(Lists.newArrayList(cache.asMap().values()));
    }

    @Override
    public List<PinGroup> findAllByPinId(String pinId) {
        return findAll().stream().filter(it -> it.id().equals(pinId)).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<PinGroup> findByName(String name) {
        return findAll().stream().filter(it -> it.name().equals(name)).findFirst();
    }
}
