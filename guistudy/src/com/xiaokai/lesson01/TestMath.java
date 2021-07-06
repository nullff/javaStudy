package com.xiaokai.lesson01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestMath {
    public static void main(String[] args) {
    new Calculator();
    }
}

/**
 * 测试简易计算机类
 */
class Calculator extends Frame{
    public Calculator()  {
        //创建三个文本框
        TextField t1 = new TextField(10);
        TextField t2 = new TextField(10);
        TextField t3 = new TextField(20);
        //创建等号按钮
        Button button = new Button("=");
        //创建+标签
        Label label = new Label("+");
        //设置布局
        setLayout(new FlowLayout());
        add(t1);
        add(label);
        add(t2);
        add(button);
        add(t3);
        pack();
        setVisible(true);
        //给等号加监听
        button.addActionListener(new MyMonitor3(t1,t2,t3));
        addWindowClosingEvent(this);

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

//监听器
class MyMonitor3 implements ActionListener{
    TextField num1,num2,num3;
    public MyMonitor3(TextField num1,TextField num2,TextField num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int n1 = Integer.parseInt(num1.getText());
        int n2 = Integer.parseInt(num2.getText());
        num3.setText(String.valueOf(n1+n2));

        num1.setText("");
        num2.setText("");
    }
}
