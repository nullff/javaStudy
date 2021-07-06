package com.xiaokai.lesson01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TestTextField {
    public static void main(String[] args) {
        new MyFrameTextField();

    }
}

/**
 * 文本框控制类，继承Frame;
 */
class MyFrameTextField extends Frame{

    public MyFrameTextField(){
        TextField textField = new TextField();
        add(textField);
        //设置监听
        textField.addActionListener(new MyMonitor2());

        //设置密码保护
        textField.setEchoChar('*');

        setVisible(true);
        pack();


    }
}

class MyMonitor2 implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        //时间的相关信息都封装在e中，通过对象e的相关方法可以获取时间的相关信息
        //getSource方法是拿到事件源，拿到这个事件的源的时候是将它当做TextField的父类对待的（Object）;
        //需要将拿到的Object向下转型，变为TextField类型

        TextField textField = (TextField) e.getSource();

        //取得文本框的内容

        System.out.println(textField.getText());

        //把文本框清空
        textField.setText("");
    }
}