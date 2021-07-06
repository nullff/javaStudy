package com.xiaokai.lesson01;

import sun.awt.WindowClosingListener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TestPanel {
    public static void main(String[] args) {
        Frame frame = new Frame("Java Frame With Panel!");
        Panel panel = new Panel(null);
        frame.setLayout(null);
        //设置的坐标是相对已整个屏幕的
        frame.setBounds(300,300,500,500);

        //设置背景颜色
        frame.setBackground(new Color(124, 47, 226, 255));

        //这里设置的坐标是相对于窗体的
        panel.setBounds(50,50,400,400);
        panel.setBackground(Color.cyan);

        //把panel容器放到frame容器中
        frame.add(panel);
        frame.setVisible(true);

        //解决关闭问题
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}
