package com.guwave.onetest.site.infrastructure.persistence.jpa;

import com.guwave.onetest.site.domain.pingroup.model.PinGroup;
import com.guwave.onetest.site.domain.pingroup.repository.PinGroupRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Optional;

public class PinGroupRepositoryJpa implements PinGroupRepository {
    @Override
    public void deletePin(String id) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public void save(PinGroup pinGroup) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public void saveBatch(List<PinGroup> pinGroups) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public Optional<PinGroup> findOne(String id) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public List<PinGroup> findAll() {
        throw new NotImplementedException("not implemented");
    }
}
