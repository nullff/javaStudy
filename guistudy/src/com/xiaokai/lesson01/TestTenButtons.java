package com.xiaokai.lesson01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestTenButtons {
    public static void main(String[] args) {
        Frame frame = new Frame("布局管理器的嵌套使用！");

        Panel panel1 = new Panel();
        Panel panel2 = new Panel();
        Panel panel3 = new Panel();
        Panel panel4 = new Panel();

        panel1.setLayout(new GridLayout(2,1));
        for (int i = 1; i <= 2; i++) {
            panel1.add(new Button("button"+i));
        }
        panel2.setLayout(new GridLayout(2,2));
        for (int i = 3; i <= 6; i++) {
            panel2.add(new Button("button"+i));
        }
        panel3.setLayout(new GridLayout(1,3));
        panel4.setLayout(new GridLayout(1,3));
        panel3.add(new Button("button7"));
        panel3.add(panel1);
        panel3.add(new Button("button8"));
        panel4.add(new Button("button9"));
        panel4.add(panel2);
        panel4.add(new Button("button10"));
        frame.setLayout(new GridLayout(2,1));
        frame.add(panel3);
        frame.add(panel4);

        frame.setSize(400,400);
        frame.setVisible(true);
        frame.pack();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
