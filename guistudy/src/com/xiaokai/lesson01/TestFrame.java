package com.xiaokai.lesson01;

import java.awt.*;

/**
 * 学习JavaGUI编写的第一个图形界面窗口
 */
public class TestFrame {
    public static void main(String[] args) {
        Frame frame = new Frame("我的第一个图形界面窗口");

        //设置窗体背景颜色
        frame.setBackground(Color.green);

        //设置窗体是否可见
        frame.setVisible(true);

        //设置初始窗体大小
        frame.setSize(400,400);

        //设置窗体出现的位置
        frame.setLocation(200,200);

        //设置能否被改变大小
        frame.setResizable(false);

    }
}
