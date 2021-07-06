package com.xiaokai.lesson01;

import java.awt.*;

public class TestGridLayout {
    public static void main(String[] args) {
        Frame frame = new Frame("TestGridLayout");

        Button button1 = new Button("button1");
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");
        Button button4 = new Button("button4");
        Button button5 = new Button("button5");
        Button button6 = new Button("button6");

        frame.setLayout(new GridLayout(3,2));

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.setSize(400,400);

        //frame.pack();//盖方法的作用是根据窗口的布局及组件的preferredSize来确定frame的最佳大小
        frame.setVisible(true);
    }
}
