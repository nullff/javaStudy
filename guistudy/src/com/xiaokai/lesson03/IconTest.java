package com.xiaokai.lesson03;

import javax.swing.*;
import java.awt.*;

public class IconTest extends JFrame implements Icon {
    public static void main(String[] args) {

        new IconTest().init();

    }

    private int height,width;

    public IconTest(){};//无参构造

    public IconTest(int height,int width){
        this.height = height;
        this.width = width;
    }//有参构造

    public void init(){
        IconTest iconTest = new IconTest(15, 15);
        JLabel jLabel = new JLabel("Icon测试", iconTest, SwingConstants.CENTER);
        Container container = getContentPane();
        container.add(jLabel);

        this.setVisible(true);
        this.setBounds(200,200,500,350);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.fillOval(x,y,width,height);
    }

    @Override
    public int getIconWidth() {
        return this.width;
    }

    @Override
    public int getIconHeight() {
        return this.height;
    }
}
