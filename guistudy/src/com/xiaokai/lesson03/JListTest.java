package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class JListTest extends JFrame {
    public static void main(String[] args) {
        new JListTest();
    }
    public JListTest(){
        super("zz");
        Container container = this.getContentPane();
        String [] contents = {"1","2","3"};
        Vector vector = new Vector();
        JList jList = new JList(contents);
        JList jList1 = new JList(vector);
        container.add(jList,BorderLayout.NORTH);
        container.add(jList1,BorderLayout.SOUTH);
        vector.add("1");
        vector.add("2");
        vector.add("3");


        this.setVisible(true);
        this.setBounds(200,200,400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
