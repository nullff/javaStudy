package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageIconTest extends JFrame {
    public static void main(String[] args) {

        new ImageIconTest();

    }

    public ImageIconTest() {
        JLabel jLabel = new JLabel("这是一个JFrame窗体，旁边是一个图片");
        URL resource = ImageIconTest.class.getResource("logo.png");
        ImageIcon imageIcon = new ImageIcon(resource);
        jLabel.setIcon(imageIcon);//为标签设置图标Icon
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);//设置标签不透明

        Container container = getContentPane();
        container.add(jLabel);
        setVisible(true);
        setBounds(300,300,500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
