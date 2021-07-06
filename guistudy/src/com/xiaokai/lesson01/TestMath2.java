package com.xiaokai.lesson01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Java的经典用法：在一个类中持有另一个类的引用
 */
public class TestMath2 {
    public static void main(String[] args) {
        new Calculator2().launchFrame();
    }
}
//做好计算机的窗体界面
class Calculator2 extends Frame{
    //把设计计算机的窗体封装成一个方法
    TextField t1,t2,t3;

    public void launchFrame(){
        //创建三个文本框
        t1 = new TextField(10);
        t2 = new TextField(10);
        t3 = new TextField(20);
        //创建等号按钮
        Button button = new Button("=");
        //设置监听
        button.addActionListener(new MyMonitor4(this));
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
    }
}

class MyMonitor4 implements ActionListener{
    Calculator2 calculator2 ;
    //获取Calculator2类的引用

    public MyMonitor4(Calculator2 calculator2) {
        this.calculator2 = calculator2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int n1 = Integer.parseInt(calculator2.t1.getText());
        int n2 = Integer.parseInt(calculator2.t2.getText());
        calculator2.t3.setText(String.valueOf(n1+n2));
        calculator2.t1.setText("");
        calculator2.t2.setText("");
    }
}