package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class JComboBoxTest extends JFrame {
    public static void main(String[] args) {
        new JComboBoxTest();
    }
    public JComboBoxTest(){
        super("zz");
        Container container = this.getContentPane();
        JComboBox jComboBox = new JComboBox();
        jComboBox.addItem(null);
        jComboBox.addItem("aaa");
        jComboBox.addItem("bbb");
        jComboBox.addItem("ccc");

        container.add(jComboBox);

        this.setVisible(true);
        this.setBounds(200,200,400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
