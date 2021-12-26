package com.guwave.onetest.site.infrastructure.persistence.json;

import com.guwave.onetest.site.infrastructure.persistence.json.po.PinGroupPo;
import com.guwave.onetest.site.infrastructure.persistence.json.po.PinPo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JsonStorage {
    private List<PinPo> pins = new ArrayList<>();
    private List<PinGroupPo> pinGroups = new ArrayList<>();
}