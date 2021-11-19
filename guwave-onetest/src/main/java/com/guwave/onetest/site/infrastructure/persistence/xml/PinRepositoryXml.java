package com.guwave.onetest.site.infrastructure.persistence.xml;

import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PinRepositoryXml implements PinRepository {
    @Override
    public  void deletePin(String id) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public void save(Pin pin) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public Optional<Pin> findOne(String id) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public List<Pin> findAll() {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public Optional<Pin> findByName(String name) {
        throw new NotImplementedException("not implemented");
    }

    @Override
    public List<Pin> findBatch(Set<String> ids) {
        throw new NotImplementedException("not implemented");
    }
}
