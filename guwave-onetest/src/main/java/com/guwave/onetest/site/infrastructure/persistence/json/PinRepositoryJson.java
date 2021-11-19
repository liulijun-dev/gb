package com.guwave.onetest.site.infrastructure.persistence.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.repository.PinRepository;
import com.guwave.onetest.site.infrastructure.persistence.json.po.PinPo;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2(topic = "PinRepositoryJson")
public class PinRepositoryJson implements PinRepository {
    private JsonStorage jsonStorage;

    public PinRepositoryJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/json_storage.json")) {
            jsonStorage = objectMapper.readValue(is, JsonStorage.class);
        }
    }

    @Override
    public void deletePin(String id) {
        jsonStorage.getPins().removeIf(it -> it.getId().equals(id));
        persist();
    }

    @Override
    public void save(Pin pin) {
        jsonStorage.getPins().add(PinPo.from(pin));
        persist();
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

    private void persist() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (OutputStream os = new FileOutputStream(getClass().getResource("/json_storage.json").getPath())) {
            objectMapper.writeValue(os, jsonStorage);
            os.flush();
        } catch (IOException e) {
            log.error("persist pin error", e);
        }
    }
}