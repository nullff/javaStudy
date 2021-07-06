package com.xiaokai.gametest.tcztest;

import javax.swing.*;
import java.net.URL;

public class Data {
    //广告位招租
    public static URL headerUrl = Data.class.getResource("header.png");
    public static URL foodUrl = Data.class.getResource("food.png");
    //头和身子
    public static URL upUrl = Data.class.getResource("up.png");
    public static URL downUrl = Data.class.getResource("down.png");
    public static URL leftUrl = Data.class.getResource("left.png");
    public static URL rightUrl = Data.class.getResource("right.png");
    public static URL bodyUrl = Data.class.getResource("body.png");

    //new ImageIcon;
    public static ImageIcon header = new ImageIcon(headerUrl);
    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downUrl);
    public static ImageIcon right = new ImageIcon(rightUrl);
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static ImageIcon body = new ImageIcon(bodyUrl);
    public static ImageIcon food = new ImageIcon(foodUrl);

}
