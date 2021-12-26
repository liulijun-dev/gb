package com.guwave.onetest.instrument.domain.instrument.repository;

import com.guwave.onetest.instrument.domain.instrument.model.InstrumentBase;

import java.util.List;
import java.util.Optional;

public interface InstrumentRepository {
    void save(InstrumentBase instrument);

    Optional<InstrumentBase> findOne(String id);

    List<InstrumentBase> findAll();

    void deleteById(String id);
}
