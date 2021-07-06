package com.xiaokai.lesson03;

import javax.swing.*;

public class JFrameTest {
    public static void main(String[] args) {
        new JFrameTest().createJFrame();
    }
    private void createJFrame(){
        JFrame jFrame = new JFrame("这是一个JFrame窗口");
        jFrame.setVisible(true);
        jFrame.setSize(500,350);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
