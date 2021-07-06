package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class JPanelTest extends JFrame {
    public static void main(String[] args) {
        new JPanelTest();
    }
    public JPanelTest(){
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2,1,10,10));

        JPanel jPanel = new JPanel(new GridLayout(1, 3));
        JPanel jPanel1 = new JPanel(new GridLayout(1, 2));
        JPanel jPanel2 = new JPanel(new GridLayout(2, 1));
        JPanel jPanel3 = new JPanel(new GridLayout(3, 2));

        jPanel.add(new JButton("1"));
        jPanel.add(new JButton("1"));
        jPanel.add(new JButton("1"));
        jPanel1.add(new JButton("2"));
        jPanel1.add(new JButton("2"));
        jPanel2.add(new JButton("3"));
        jPanel2.add(new JButton("3"));
        jPanel3.add(new JButton("4"));
        jPanel3.add(new JButton("4"));
        jPanel3.add(new JButton("4"));
        jPanel3.add(new JButton("4"));
        jPanel3.add(new JButton("4"));
        jPanel3.add(new JButton("4"));

        container.add(jPanel);
        container.add(jPanel1);
        container.add(jPanel2);
        container.add(jPanel3);

        this.setVisible(true);
        this.setBounds(200,200,500,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
