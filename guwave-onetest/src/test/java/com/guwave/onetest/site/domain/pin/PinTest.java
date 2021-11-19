package com.guwave.onetest.site.domain.pin;

import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.helper.UUIDUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PinTest {

    // bad path
    @Test
    public void should_throw_exception_when_create_pin_given_pin_name_is_blank() {
        String pinName = "";

        assertThrows(IllegalArgumentException.class, () -> Pin.create(UUIDUtil.uuid(), pinName));
    }

    // happy path
    @Test
    public void should_success_when_rename_pin_given_invalid_string() {
        String newPinName = "pin";
        Pin pin = Pin.create(UUIDUtil.uuid(), "hello");

        pin.renamePin(newPinName);

        assertEquals(newPinName, pin.getName());
    }
}
