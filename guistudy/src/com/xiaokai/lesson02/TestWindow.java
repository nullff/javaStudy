package com.xiaokai.lesson02;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestWindow {
    public static void main(String[] args) {
    new WindowsFrame("testWindow");
    }
}
class WindowsFrame extends Frame{
    public WindowsFrame(String title)  {
        super(title);
        setBounds(200,200,400,400);
        setBackground(new Color(220, 176, 88));
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("你点击了X！");
                System.exit(0);
            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("窗口被激活！");
            }
        });
    }
}
