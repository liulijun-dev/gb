package com.gb.onetest.mvvm.demo.domain.testflow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class EvaluateSetting {
    private String testNumber;
    private String testName;
    private String testText;
    private Integer lowLimit;
    private Integer highLimit;
    private String scalingFactor;
    private String unit;
    private String softBin;
    private String hardBin;
    private String exportDataTo;
    private List<String> measurePins;
}
