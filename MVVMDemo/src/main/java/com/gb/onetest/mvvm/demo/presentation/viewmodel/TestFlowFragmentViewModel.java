package com.gb.onetest.mvvm.demo.presentation.viewmodel;

import com.gb.onetest.mvvm.demo.domain.testflow.model.EvaluateSetting;
import com.gb.onetest.mvvm.demo.domain.testflow.model.TestFlow;
import com.gb.onetest.mvvm.demo.domain.testflow.repository.GrpcTestFlowRepository;

import java.util.List;
import java.util.Optional;


public class TestFlowFragmentViewModel {
    private final GrpcTestFlowRepository grpcTestFlowRepository;

    public TestFlowFragmentViewModel(GrpcTestFlowRepository grpcTestFlowRepository) {
        this.grpcTestFlowRepository = grpcTestFlowRepository;
    }

    public Optional<TestFlow> getFlows(String id) {
        return grpcTestFlowRepository.getTestFlowById(id);
    }

    public List<EvaluateSetting> getEvaluationSettingsByXXX(String xxx) {
        return grpcTestFlowRepository.getEvaluationSettingsByXXX(xxx);
    }
}
