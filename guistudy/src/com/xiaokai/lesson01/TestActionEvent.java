package com.xiaokai.lesson01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestActionEvent {
    public static void main(String[] args) {
        Frame frame = new Frame("TestActionEvent");
        Button button = new Button("Press Me");
        //创建一个监听对象
        MyActionListener1 myActionListener1 = new MyActionListener1();
        //将监听对象加入到button里，监听按钮动作；
        //按钮触发打击事件，返回监听对象e；
        //然后自动执行actionPerformed
        button.addActionListener(myActionListener1);

        frame.add(button,BorderLayout.CENTER);
        frame.pack();

        addWindowClosingEvent(frame);

        frame.setVisible(true);


    }

    //关闭窗口
    private static void addWindowClosingEvent(Frame frame){
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
class MyActionListener1 implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("A button has been pressed!");
    }
}
