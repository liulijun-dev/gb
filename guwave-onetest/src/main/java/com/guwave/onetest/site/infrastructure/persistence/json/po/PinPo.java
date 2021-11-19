package com.guwave.onetest.site.infrastructure.persistence.json.po;

import com.guwave.onetest.site.domain.pin.model.Pin;
import com.guwave.onetest.site.domain.pin.model.SiteId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PinPo {
    private String id;
    private String name;
    private List<SiteId> siteIds;

    public static PinPo from(Pin pin) {
        return new PinPo(pin.id(), pin.getName(), pin.getSiteIds());
    }

    public Pin to() {
        return new Pin(id, name, siteIds);
    }
}
