package com.xiaokai.lesson01;

import java.awt.*;

public class TestBorderLayout {
    public static void main(String[] args) {
        Frame frame = new Frame("TestBorderLayout");
        Button button1 = new Button("button1");
        Button button2 = new Button("button2");
        Button button3 = new Button("button3");
        Button button4 = new Button("button4");
        Button button5 = new Button("button5");

        frame.add(button1,BorderLayout.EAST);
        frame.add(button2,BorderLayout.WEST);
        frame.add(button3,BorderLayout.SOUTH);
        frame.add(button4,BorderLayout.NORTH);
        frame.add(button5,BorderLayout.CENTER);
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}
