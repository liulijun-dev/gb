package com.guwave.onetest.site.domain.pingroup.repository;

import com.guwave.onetest.site.domain.pingroup.model.PinGroup;

import java.util.List;
import java.util.Optional;

public interface PinGroupRepository {
    void deletePin(String pinGroupId, String pinId);

    void delete(String id);

    void save(PinGroup pinGroup);

    void saveBatch(List<PinGroup> pinGroups);

    Optional<PinGroup> findOne(String id);

    List<PinGroup> findAll();

    List<PinGroup> findAllByPinId(String pinId);

    Optional<PinGroup> findByName(String name);
}
