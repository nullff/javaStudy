package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class ImageButtonTest extends JFrame {
    public static void main(String[] args) {
        new ImageButtonTest();
    }
    public ImageButtonTest(){
        Container container = this.getContentPane();
        Icon icon = new ImageIcon(ImageButtonTest.class.getResource("logo.png"));

        JButton jButton = new JButton();
        jButton.setIcon(icon);

        jButton.setToolTipText("图片按钮");

        container.add(jButton);
        this.setVisible(true);
        this.setSize(400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
