package com.xiaokai.lesson02;

import java.awt.*;

public class TestPaint {
    public static void main(String[] args) {
        new MyPaint().launchFrame();
    }
}
class MyPaint extends Frame{
    public void launchFrame(){
        setBounds(200,200,400,400);
        setVisible(true);
    }
    public void paint(Graphics graphics){
        //设置画笔的颜色
        graphics.setColor(Color.red);
        graphics.fillOval(100,100,100,100);
        graphics.setColor(Color.green);
        graphics.fillRect(150,200,200,200);
        Color c = graphics.getColor();
        graphics.setColor(c);

        System.out.println("gogogo");
    }
}
