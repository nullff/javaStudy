package com.xiaokai.lesson01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Java的经典用法：在一个类中持有另一个类的引用
 */
public class TestMath3 {
    public static void main(String[] args) {
        new Calculator2().launchFrame();
    }
}
//做好计算机的窗体界面
class Calculator3 extends Frame{
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
        button.addActionListener(new MyMonitor5());
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

    /**
     * 使用内部类的好处：
     * 第一个就是可以畅通无阻的访问外部类的所有成员变量和方法；
     * 相当于默认内部类拥有外部类对象的引用；
     */
    class MyMonitor5 implements ActionListener{
//        Calculator3 calculator3 ;
//        //获取Calculator3类的引用
//
//        public MyMonitor5(Calculator3 calculator3) {
//            this.calculator3 = calculator3;
//        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int n1 = Integer.parseInt(t1.getText());
            int n2 = Integer.parseInt(t2.getText());
            t3.setText(String.valueOf(n1+n2));
            t1.setText("");
            t2.setText("");
        }
    }
}

//class MyMonitor5 implements ActionListener{
//    Calculator3 calculator3 ;
//    //获取Calculator3类的引用
//
//    public MyMonitor5(Calculator3 calculator3) {
//        this.calculator3 = calculator3;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        int n1 = Integer.parseInt(calculator3.t1.getText());
//        int n2 = Integer.parseInt(calculator3.t2.getText());
//        calculator3.t3.setText(String.valueOf(n1+n2));
//        calculator3.t1.setText("");
//        calculator3.t2.setText("");
//    }
//}