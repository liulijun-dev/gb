package com.guwave.onetest.site.infrastructure.persistent.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.model.SiteId;
import com.guwave.onetest.site.infrastructure.persistence.json.JsonStorage;
import com.guwave.onetest.site.infrastructure.persistence.json.PinRepositoryJson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PinRepositoryJsonTest {
    private PinRepositoryJson pinRepositoryJson;

    @BeforeEach
    public void setup() throws IOException {
        cleanJsonStorage();
        pinRepositoryJson = new PinRepositoryJson();
    }

    @AfterEach
    public void clear() throws IOException {
        cleanJsonStorage();
    }

    @Test
    public void should_save_success_when_delete_pin_given_correct_pin_id() throws IOException {
        pinRepositoryJson.save(new Pin("1", "test", Lists.newArrayList(new SiteId("2"), new SiteId("4"))));

        JsonStorage jsonStorage = readJsonStorage();

        assertEquals(1, jsonStorage.getPins().size());
        assertEquals("1", jsonStorage.getPins().get(0).getId());
        assertEquals("test", jsonStorage.getPins().get(0).getName());
    }

    private JsonStorage readJsonStorage() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/json_storage.json")) {
            return objectMapper.readValue(is, JsonStorage.class);
        }
    }

    private void cleanJsonStorage() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonStorage jsonStorage = new JsonStorage();

        try (OutputStream os = new FileOutputStream(getClass().getResource("/json_storage.json").getPath())) {
            objectMapper.writeValue(os, jsonStorage);
            os.flush();
        }
    }
}
