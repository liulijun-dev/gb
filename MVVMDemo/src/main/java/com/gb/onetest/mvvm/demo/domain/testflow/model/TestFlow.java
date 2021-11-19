package com.gb.onetest.mvvm.demo.domain.testflow.model;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class TestFlow {
    private final Map<TestFlowNodeType, List<TestFlowDescription>> flows;

    public TestFlow(Map<TestFlowNodeType, List<TestFlowDescription>> flows) {
        this.flows = flows;
    }
}
