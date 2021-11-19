package com.gb.onetest.mvvm.demo.data.server;

import com.gb.onetest.mvvm.demo.domain.testflow.model.EvaluateSetting;
import com.gb.onetest.mvvm.demo.domain.testflow.model.TestFlow;
import com.gb.onetest.mvvm.demo.domain.testflow.model.TestFlowDescription;
import com.gb.onetest.mvvm.demo.domain.testflow.model.TestFlowNodeType;
import com.gb.onetest.mvvm.demo.domain.testflow.repository.GrpcTestFlowRepository;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GrpcTestFlowRepositoryImpl implements GrpcTestFlowRepository {
    @Override
    public Optional<TestFlow> getTestFlowById(String id) {
        return Optional.of(mockTestFlow());
    }

    @Override
    public List<EvaluateSetting> getEvaluationSettingsByXXX(String id) {
        return mockEvaluationSettings();
    }

    private TestFlow mockTestFlow() {
        TestFlowDescription simpleTestSuite = new TestFlowDescription("Simple_TestSuite", "Simple_TestInstance", "Simple_TM", "StopOnFail", "NA", "");
        TestFlowDescription aaa = new TestFlowDescription("AAA", "Simple_TestInstance", "Simple_TM", "StopOnFail", "NA", "");

        List<TestFlowDescription> testFlowDescriptions = new ArrayList<>();
        testFlowDescriptions.add(simpleTestSuite);
        testFlowDescriptions.add(aaa);

        Map<TestFlowNodeType, List<TestFlowDescription>> flows = new HashMap<>();
        flows.put(TestFlowNodeType.MAIN_FLOW, testFlowDescriptions);

        return new TestFlow(flows);
    }

    private List<EvaluateSetting> mockEvaluationSettings() {
        List<EvaluateSetting> evaluateSettings = new ArrayList<>();
        EvaluateSetting evaluateSettingOne = EvaluateSetting.builder()
            .testNumber(String.valueOf(RandomUtils.nextInt()))
            .testText("1")
            .testName("ptdCurrent")
            .testText("ptdC")
            .lowLimit(0)
            .highLimit(1)
            .scalingFactor("")
            .softBin("21 - OS VDD")
            .hardBin("2 - OS")
            .exportDataTo("")
            .measurePins(Collections.emptyList())
            .build();
        EvaluateSetting evaluateSettingTwo = EvaluateSetting.builder()
            .testNumber(String.valueOf(RandomUtils.nextInt()))
            .testText("1")
            .testName("ptdCurrent")
            .testText("ptdC")
            .lowLimit(0)
            .highLimit(1)
            .scalingFactor("")
            .softBin("21 - OS VDD")
            .hardBin("2 - OS")
            .exportDataTo("")
            .measurePins(Collections.emptyList())
            .build();
        evaluateSettings.add(evaluateSettingOne);
        evaluateSettings.add(evaluateSettingTwo);
        return evaluateSettings;
    }
}
