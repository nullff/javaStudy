package com.xiaokai.gametest.tcztest;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("贪吃猪");
        frame.setBounds(100,100,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.add(new GamePanel());
        frame.setVisible(true);
    }
}
