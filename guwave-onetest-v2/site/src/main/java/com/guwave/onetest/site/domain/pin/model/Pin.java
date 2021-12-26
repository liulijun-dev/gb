package com.guwave.onetest.site.domain.pin.model;

import com.guwave.onetest.common.domain.Entity;
import com.guwave.onetest.common.util.UUIDUtil;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;

@Log4j2(topic = "Pin")
@Getter
public class Pin extends Entity<String> {
    private String name;

    public static Pin create(@NonNull String name) {
        return new Pin(UUIDUtil.uuid(), name);
    }

    public Pin(String id, String name) {
        super(id);
        this.name = name;
    }

    public void renamePin(String newName) {
        this.name = newName;
    }
}
