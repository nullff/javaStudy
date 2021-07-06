package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class JSCrollPaneTest extends JFrame {
    public static void main(String[] args) {
        new JSCrollPaneTest();
    }
    public JSCrollPaneTest(){
        Container container = this.getContentPane();

        TextArea textArea = new TextArea(20, 50);
        textArea.setText("关大山");

        JScrollPane jScrollPane = new JScrollPane(textArea);
        container.add(jScrollPane);

        this.setVisible(true);
        this.setBounds(200,200,300,150);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
