package com.gb.onetest.mvvm.demo.presentation.ui;

import com.gb.onetest.mvvm.demo.presentation.ui.testflow.TestFlowFragment;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends AbstractFrame{
    public static void main(String[] args) {
        JFrame main = new AbstractFrame(){

        };
        main.add(new TestFlowFragment(), BorderLayout.CENTER);
        main.setVisible(true);
    }
}
