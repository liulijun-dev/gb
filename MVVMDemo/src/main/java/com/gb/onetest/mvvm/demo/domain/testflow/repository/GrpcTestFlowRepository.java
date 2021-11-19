package com.gb.onetest.mvvm.demo.domain.testflow.repository;

import com.gb.onetest.mvvm.demo.domain.testflow.model.EvaluateSetting;
import com.gb.onetest.mvvm.demo.domain.testflow.model.TestFlow;

import java.util.List;
import java.util.Optional;

public interface GrpcTestFlowRepository {
    Optional<TestFlow> getTestFlowById(String id);

    List<EvaluateSetting> getEvaluationSettingsByXXX(String xxx);
}
