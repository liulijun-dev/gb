package com.gb.onetest.mvvm.demo.presentation.ui.testflow;

import com.gb.onetest.mvvm.demo.data.server.GrpcTestFlowRepositoryImpl;
import com.gb.onetest.mvvm.demo.domain.testflow.model.EvaluateSetting;
import com.gb.onetest.mvvm.demo.domain.testflow.model.TestFlow;
import com.gb.onetest.mvvm.demo.domain.testflow.model.TestFlowDescription;
import com.gb.onetest.mvvm.demo.domain.testflow.model.TestFlowNodeType;
import com.gb.onetest.mvvm.demo.presentation.viewmodel.TestFlowFragmentViewModel;
import net.sds.mvvm.collections.ObservableArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestFlowFragment extends JPanel {
    private final TestFlowFragmentViewModel viewModel;
    private final JTable testFlowTable;
    private final JTable evaluateSetting;
    private int lastSelectedRow = -1;

    public TestFlowFragment() {
        super(new BorderLayout());
        testFlowTable = new JTable();
        evaluateSetting = new JTable();
        viewModel = new TestFlowFragmentViewModel(new GrpcTestFlowRepositoryImpl());
        initTestFlowTable(testFlowTable);
        initEvaluateSetting(evaluateSetting);

        add(new JScrollPane(testFlowTable), BorderLayout.CENTER);
        add(new JScrollPane(evaluateSetting), BorderLayout.SOUTH);
    }

    private void initTestFlowTable(JTable testFlowTable) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Flow Node");
        tableModel.addColumn("Test Instance");
        tableModel.addColumn("Test Method");
        tableModel.addColumn("Flow Node Configuration");
        tableModel.addColumn("Test Result");
        tableModel.addColumn("Description");

        testFlowTable.setModel(tableModel);

        testFlowTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int currentSelectedRow = testFlowTable.getSelectedRow();
                if (currentSelectedRow == lastSelectedRow) {
                    return;
                }
                lastSelectedRow = currentSelectedRow;
                String flowNode = tableModel.getValueAt(lastSelectedRow, 0).toString();
                fillEvaluationSetting(flowNode);
            }
        });

        TestFlow testFlow = viewModel.getFlows("123").orElse(null);
        if (testFlow != null) {
            Map<TestFlowNodeType, List<TestFlowDescription>> testFlows = testFlow.getFlows();
            List<TestFlowDescription> testFlowDescriptions = testFlows.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

            for (TestFlowDescription testFlowDescription : testFlowDescriptions) {
                tableModel.addRow(new String[] {
                    testFlowDescription.getFlowNode(),
                    testFlowDescription.getTestInstance(),
                    testFlowDescription.getTestMethod(),
                    testFlowDescription.getFlowNodeConfiguration(),
                    testFlowDescription.getTestResult(),
                    testFlowDescription.getDescription()
                });
            }
        }
    }

    private void initEvaluateSetting(JTable evaluateSetting) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Test Number");
        tableModel.addColumn("Test Name");
        tableModel.addColumn("Test Text");
        tableModel.addColumn("Low Limit");
        tableModel.addColumn("High Limit");
        tableModel.addColumn("Scaling Factor");
        tableModel.addColumn("Unit");
        tableModel.addColumn("SoftBin");
        tableModel.addColumn("HardBin");
        tableModel.addColumn("Export Data to");
        tableModel.addColumn("Measure Pins");

        evaluateSetting.setModel(tableModel);
    }

    private void fillEvaluationSetting(String xxx) {
        List<EvaluateSetting> evaluateSettings = viewModel.getEvaluationSettingsByXXX("123");
        DefaultTableModel tableModel = (DefaultTableModel) evaluateSetting.getModel();
        int rowCount = tableModel.getRowCount();
        for (int i=rowCount-1; i>=0; i--) {
            tableModel.removeRow(i);
        }

        for (EvaluateSetting es : evaluateSettings) {
            tableModel.addRow(new Object[] {es.getTestNumber(),
                es.getTestName(),
                es.getTestText(),
                es.getLowLimit(),
                es.getHighLimit(),
                es.getScalingFactor(),
                es.getUnit(),
                es.getUnit(),
                es.getSoftBin(),
                es.getHardBin(),
                es.getExportDataTo(),
                es.getMeasurePins()});
        }
        tableModel.fireTableDataChanged();
    }
}
