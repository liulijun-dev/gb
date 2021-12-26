package com.guwave.onetest.site.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AddConnectionDto {
    @NotBlank(message = "site id cannot be blank")
    private final String siteId;
    @NotBlank(message = "pin id cannot be blank")
    private final String pinId;
    @NotBlank(message = "instrument id cannot be blank")
    private final String instrumentId;
    @NotNull(message = "channel cannot be null")
    private final Integer channel;
}
