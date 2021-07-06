package com.xiaokai.lesson01;

import java.awt.*;

public class TestFlowLayout {
    public static void main(String[] args) {
        Frame frame = new Frame("FlowLayout");
        //使用Button类创建按钮
        Button button1 = new Button("button1");
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");
        Button button4 = new Button("button4");
        Button button5 = new Button("button5");
        //使用FlowLayout布局，默认水平居中
        //frame.setLayout(new FlowLayout());
        //frame.setLayout(new FlowLayout(FlowLayout.LEFT,20,40));
        frame.setLayout(new FlowLayout(FlowLayout.RIGHT));
        frame.setSize(400,400);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.setVisible(true);
    }
}
