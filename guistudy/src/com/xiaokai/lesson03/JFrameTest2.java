package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class JFrameTest2 extends JFrame {
    public static void main(String[] args) {
        new JFrameTest2().init();
    }
    private void init(){
        this.setVisible(true);
        this.setSize(500,350);
        this.setTitle("小凯");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel jLabel = new JLabel("HelloWorld!");

        jLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Container container = this.getContentPane();//获取容器
        container.add(jLabel);//将标签添加到容器
        container.setBackground(Color.yellow);
    }
}
