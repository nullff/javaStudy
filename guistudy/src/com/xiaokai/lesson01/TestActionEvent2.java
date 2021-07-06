package com.xiaokai.lesson01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestActionEvent2 {
    public static void main(String[] args) {
        Frame frame = new Frame("TestActionEvent");
        Button start = new Button("start");
        Button stop = new Button("stop");

        MyMonitor myMonitor = new MyMonitor();

        start.addActionListener(myMonitor);
        stop.addActionListener(myMonitor);

        stop.setActionCommand("GameOver!");

        frame.setLayout(new GridLayout(1,2));

        frame.add(start);
        frame.add(stop);

        frame.pack();

        frame.setVisible(true);
    }
}
class MyMonitor implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
