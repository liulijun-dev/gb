package com.guwave.onetest.bff.interfaces.local.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public final class PinGroupDetailDto {
    private String id;
    private String name;
    private List<PinDetailDto> pins;

}
