package com.xiaokai.lesson02;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 代码测试，画点
 */
public class TestMouse {
    public static void main(String[] args) {
        new MyFrame("画图");
    }
}
class MyFrame extends Frame{
    ArrayList points = null;
    public MyFrame(String s)  {
        super(s);
        setBackground(Color.white);
        setBounds(200,200,500,400);
        setVisible(true);
        addMouseListener(new Monitor());
        points = new ArrayList();
    }

    public void addPoint(Point p){
        points.add(p);
    }

    public void paint(Graphics graphics){
        Iterator iterator = points.iterator();
        while (iterator.hasNext()){
            Point p = (Point) iterator.next();
            graphics.setColor(Color.blue);
            graphics.fillOval(p.x,p.y,5,5);
        }
    }

    public class Monitor extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            MyFrame myFrame = (MyFrame) e.getSource();
            myFrame.addPoint(new Point(e.getX(),e.getY()));
            myFrame.repaint();
        }
    }
}
