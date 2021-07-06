package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class JRadioButtonTest extends JFrame {
    public static void main(String[] args) {
        new JRadioButtonTest();
    }
    public JRadioButtonTest(){
        Container container = this.getContentPane();

        //ImageIcon icon = new ImageIcon(JRadioButtonTest.class.getResource("logo.png"));

        JRadioButton jRadioButton1 = new JRadioButton("JRadioButton1");
        JRadioButton jRadioButton2 = new JRadioButton("JRadioButton2");
        JRadioButton jRadioButton3 = new JRadioButton("JRadioButton3");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        buttonGroup.add(jRadioButton3);

        container.add(jRadioButton1,BorderLayout.NORTH);
        container.add(jRadioButton2,BorderLayout.CENTER);
        container.add(jRadioButton3,BorderLayout.SOUTH);

        this.setVisible(true);
        this.setBounds(200,200,400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
