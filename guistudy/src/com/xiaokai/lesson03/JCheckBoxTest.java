package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class JCheckBoxTest extends JFrame {
    public static void main(String[] args) {
        new JCheckBoxTest();
    }
    public JCheckBoxTest(){
        super("zz");
        Container container = this.getContentPane();
        JCheckBox jCheckBox1 = new JCheckBox("abc");
        JCheckBox jCheckBox2 = new JCheckBox("abc");
        container.add(jCheckBox1,BorderLayout.SOUTH);
        container.add(jCheckBox2,BorderLayout.NORTH);

        this.setVisible(true);
        this.setBounds(200,200,400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
