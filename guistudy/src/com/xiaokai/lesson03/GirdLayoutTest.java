package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class GirdLayoutTest extends JFrame {
    public static void main(String[] args) {
        new GirdLayoutTest();
    }
    public GirdLayoutTest(){
        Container container = this.getContentPane();
        this.setLayout(new GridLayout(7,3,5,5));//前两个参数是7行3列，后两个是网格的间距5

        for (int i = 0; i < 20; i++) {
            container.add(new JButton("按钮"+i));
        }

        this.setVisible(true);
        this.setBounds(200,200,400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
