package com.xiaokai.listener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestPanel {
    public static void main(String[] args) {
        Frame frame = new Frame("你好世界");
        Panel panel = new Panel(null);
        frame.setLayout(null);

        frame.setBounds(300,300,500,500);
        frame.setBackground(new Color(163, 17, 17));

        panel.setBounds(50,50,300,300);
        panel.setBackground(new Color(62, 186, 21));

        frame.add(panel);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });

    }
}
