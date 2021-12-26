package com.guwave.onetest.site.infrastructure.persistence.json.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PinGroupPo {
    private String id;
    private String name;
    private List<String> pins;
}
