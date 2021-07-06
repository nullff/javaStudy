package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutDemo extends JFrame {
    private String[] border = {BorderLayout.CENTER,BorderLayout.NORTH,BorderLayout.SOUTH,BorderLayout.WEST,BorderLayout.EAST};
    private String[] button = {"中","北","南","西","东"};

    public static void main(String[] args) {
        new BorderLayoutDemo();
    }

    public BorderLayoutDemo(){
        super("方向");
        Container container = this.getContentPane();
        this.setLayout(new BorderLayout());
        //添加循环按钮
        for (int i = 0; i < button.length; i++) {
            container.add(border[i],new JButton(button[i]));
        }
        this.setVisible(true);
        this.setBounds(200,200,500,350);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
