package com.guwave.onetest.site.infrastructure.persistence.sqlite;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.guwave.onetest.site.domain.site.model.Site;
import com.guwave.onetest.site.domain.site.repository.SiteRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SiteRepositoryImpl implements SiteRepository {
    private final LoadingCache<String, Site> cache = CacheBuilder.newBuilder().build(new CacheLoader<>() {
        @Override
        public Site load(String key) {
            return null;
        }
    });

    @Override
    public Optional<Site> findOne(String id) {
        return Optional.ofNullable(cache.getIfPresent(id));
    }

    @Override
    public void deleteById(String id) {
         cache.invalidate(id);
    }

    @Override
    public void save(Site site) {
        cache.put(site.id(), site);
    }

    @Override
    public List<Site> findAll() {
        return Collections.unmodifiableList(Lists.newArrayList(cache.asMap().values()));
    }

    @Override
    public List<Site> findBatch(Set<String> ids) {
        return Collections.unmodifiableList(Lists.newArrayList(cache.getAllPresent(ids).values()));
    }

    @Override
    public void saveBatch(List<Site> sites) {
        sites.forEach(this::save);
    }

    @Override
    public List<Site> findAllByPinId(String id) {
        return findAll().stream().filter(it -> it.id().equals(id)).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public long count() {
        return cache.size();
    }

    @Override
    public List<Site> findAllBySiteNumberLargeThan(long number) {
        return findAll().stream().filter(it -> it.number() > number).collect(Collectors.toUnmodifiableList());
    }
}
