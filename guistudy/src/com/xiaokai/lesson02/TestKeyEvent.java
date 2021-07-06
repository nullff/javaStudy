package com.xiaokai.lesson02;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestKeyEvent {
    public static void main(String[] args) {
        new KeyFrame("keyframe");
    }
}
class KeyFrame extends Frame{
    public KeyFrame(String title)  {
        super(title);
        setBounds(200,200,400,400);
        setBackground(new Color(220, 176, 88));
        setVisible(true);
        addKeyListener(new KeyMonitor());
    }
    class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            if (keycode == KeyEvent.VK_UP){
                System.out.println("你按下了上方向键！");
            }
        }
    }
}
