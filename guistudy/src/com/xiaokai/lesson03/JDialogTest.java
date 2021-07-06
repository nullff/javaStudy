package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDialogTest extends JDialog {
    public static void main(String[] args) {
        new JDialogTest();
    }
    public JDialogTest() {
        super(new MyJFrame(),"这是一个JDialog弹窗",false);
        Container container = this.getContentPane();
        container.add(new JLabel("guanxiaokai!"));

        setBounds(300,300,500,350);
        //setVisible(true);
    }

}

class MyJFrame extends JFrame{
    public MyJFrame() {
        super("父窗口");
        this.setVisible(true);
        this.setBounds(200,200,600,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container container = this.getContentPane();

        JButton jButton = new JButton("点击弹出对话框");
        jButton.setBounds(30,30,100,40);
        container.setLayout(null);
        //jButton.setBounds(30,30,200,50);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JDialogTest().setVisible(true);
            }
        });
        container.add(jButton);

    }

}
