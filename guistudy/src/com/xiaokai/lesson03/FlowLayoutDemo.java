package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutDemo extends JFrame {
    public static void main(String[] args) {
        new FlowLayoutDemo();
    }
    public FlowLayoutDemo(){
        Container container = this.getContentPane();
        this.setLayout(new FlowLayout(2,10,10));
        //设置流式布局，2是右对齐，0是左对齐，默认1是居中对齐；后两个参数是表示水平和垂直间隔；
        for (int i = 0; i < 10; i++) {
            container.add(new JButton("按钮"+i));
        }
        this.setBounds(200,200,300,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
