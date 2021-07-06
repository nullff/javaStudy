package com.xiaokai.lesson01;

import java.awt.*;

public class TestMultiFrame {
    public static void main(String[] args) {
        MyFrame frame1 = new MyFrame(100, 100, 200, 200, new Color(206, 21, 114));
        MyFrame frame2 = new MyFrame(300, 100, 200, 200, Color.CYAN);
        MyFrame frame3 = new MyFrame(100, 300, 200, 200, Color.MAGENTA);
        MyFrame frame4 = new MyFrame(300, 300, 200, 200, Color.RED);



    }
}
/**
 * 自定义一个MyFrame类，并且从Frame类继承属性和方法
 *
 */
class MyFrame extends Frame{
    //定义一个静态成员变量，用来记录创建的窗口的数目；
    static int id = 0;
    //自定义构成方法，方法体内部使用super调用父类Frame的构造方法；
    public MyFrame(int x,int y,int w,int h,Color color) {
        super("MyFrame"+(++id));
        setBackground(color);
        setLayout(null);
        setBounds(x,y,w,h);
        setVisible(true);
    }
}
