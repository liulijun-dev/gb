package com.gb.onetest.mvvm.demo.domain.testflow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TestFlowDescription {
    private String flowNode;
    private String testInstance;
    private String testMethod;
    private String flowNodeConfiguration;
    private String testResult;
    private String description;
}
