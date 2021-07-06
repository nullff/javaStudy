package com.xiaokai.gametest.test2048;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Game {
    //存储颜色的类
    private static class Color{
        public int fontColor,bgColor;//字体颜色，背景颜色
        public Color(int fc,int bgc){
            fontColor = fc;
            bgColor = bgc;
        }
    }
    JFrame mainJFrame;//定义主窗口对象
    JLabel[] jLabels;//定义方块，用JLable表示
    int[] datas = new int[]{
            0,0,0,0,
            0,0,0,0,
            0,0,0,0,
            0,0,0,0
    };//定义每个方块的初始数值
    int[] temp = new int[4];
    int[] temp2 = new int[16];//检测方块是否有合并；

    List emptyBlocks = new ArrayList<Integer>(16);//用来存放空方块

    //用来存放各个数值的颜色使用Map;
    static HashMap<Integer,Color> colorMap = new HashMap<Integer,Color>(){
        {
            put(0,new Color(0x776e65,0xCDC1B4));
            put(2,new Color(0x776e65,0xeee4da));
            put(4,new Color(0x776e65,0xede0c8));
            put(8,new Color(0xf9f6f2,0xf2b179));
            put(16,new Color(0xf9f6f2,0xFFE4C4));
            put(32,new Color(0xf9f6f2,0xFFDAB9));
            put(64,new Color(0xf9f6f2,0xFFD700));
            put(128,new Color(0xf9f6f2,0xFF7F24));
            put(256,new Color(0xf9f6f2,0xF5FFFA));
            put(512,new Color(0xf9f6f2,0xF5F678));
            put(1024,new Color(0xf9f6f2,0xFF3E11));
            put(2048,new Color(0xf9f6f2,0xFF3E96));
        }
    };

    //构造方法
    public Game(){
        initGameFrame();
        initGame();
        refresh();
    }

    //initGame()开局时生成两个2的方块和一个4的方块
    private void initGame(){
        for (int i = 0; i < 2; i++) {
            generateBlock(datas,2);
        }
        generateBlock(datas,4);

    }
    //随机生成2或者4的方块
    private void randomGenerate(int arr[]){
        int random = (int) (Math.random()*10);
        if (random > 5){
            generateBlock(arr,4);
        }else {
            generateBlock(arr,2);
        }

    }
    //随机生成新的方块（参数：生成的方块的数值）
    private void generateBlock(int arr[],int num){
        emptyBlocks.clear();

        for (int i = 0; i < 16; i++) {
            if (arr[i] == 0){
                emptyBlocks.add(i);
            }
        }
        int len = emptyBlocks.size();
        if (len == 0){
            return;
        }
        int pos = (int) (Math.random()*100) % len;
        arr[(int) emptyBlocks.get(pos)] = num;
        refresh();
    }
    //判定胜负并做终局处理
    private  void judge(int arr[]){
        if (isWin(arr)){
            JOptionPane.showMessageDialog(null,"恭喜！你已经成功凑出2048的方块","你赢了！",JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
        if (isEnd(arr)){
            JOptionPane.showMessageDialog(null,"抱歉，你没有凑出2048的方块，你的最大成绩是："+getMax(arr),"游戏结束", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
    }
    //判断玩家是否胜利
    private boolean isWin(int arr[]){
        for (int i : arr) {
            if(i >= 2048){
                return true;
            }
        }
        return false;
    }
    //判断游戏是否结束，如果上下左右移动合并后均无法产生空块，返回TRUE，表示游戏结束；
    private boolean isEnd(int arr[]){
        int [] tmp = new int[16];
        int isend = 0;

        System.arraycopy(arr,0,tmp,0,16);
        left(tmp);
        if(isNoBlock(tmp)){
            isend++;
        }

        System.arraycopy(arr,0,tmp,0,16);
        right(tmp);
        if(isNoBlock(tmp)){
            isend++;
        }

        System.arraycopy(arr,0,tmp,0,16);
        up(tmp);
        if(isNoBlock(tmp)){
            isend++;
        }

        System.arraycopy(arr,0,tmp,0,16);
        down(tmp);
        if(isNoBlock(tmp)){
            isend++;
        }

        if (isend == 4){
            return true;
        }else {
            return false;
        }
    }
    //判断是否无方块
    private boolean isNoBlock(int arr[]){
        for (int i : arr) {
            if (i == 0){
                return false;
            }
        }
        return true;
    }
    //获取到最大的方块值
    private int getMax(int arr[]){
        int max = arr[0];
        for (int i : arr) {
            if (i >= max){
                max = i;
            }
        }
        return max;
    }
    //刷新每个方块显示的数据；
    private void refresh(){
        JLabel j;
        for (int i = 0; i < 16; i++) {
            int arr = datas[i];
            j = jLabels[i];

            if (arr == 0){
                j.setText("");
            }else if (arr >= 1024){
                j.setFont(new Font("Dialog",1,42));
                j.setText(String.valueOf(datas[i]));
            }else {
                j.setFont(new Font("Dialog",1,50));
                j.setText(String.valueOf(arr));
            }

            Color currColor = colorMap.get(arr);
            j.setBackground(new java.awt.Color(currColor.bgColor));
            j.setForeground(new java.awt.Color(currColor.fontColor));
        }
    }
    //初始化游戏窗口，等一些其他操作
    private void initGameFrame(){
        //创建JFrame以及做一些设置
        mainJFrame = new JFrame("2048 Game");
        mainJFrame.setSize(500,500);
        mainJFrame.setResizable(false);//固定窗口
        mainJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainJFrame.setLocationRelativeTo(null);

        mainJFrame.setLayout(new GridLayout(4,4));
        mainJFrame.getContentPane().setBackground(new java.awt.Color(0xCDC1B4));

        //添加按键监听
        mainJFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.arraycopy(datas,0,temp2,0,16);

                //根据按键不同，调取不同的处理函数
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        up(datas);
                        break;
                    case KeyEvent.VK_DOWN:
                        down(datas);
                        break;
                    case KeyEvent.VK_LEFT:
                        left(datas);
                        break;
                    case KeyEvent.VK_RIGHT:
                        right(datas);
                        break;
                }

                //判断是否有方块合并，若有，生成新的方块，没有不产生新方块
                if (!Arrays.equals(datas,temp2)){
                    randomGenerate(datas);
                }
                refresh();
                judge(datas);

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //使用16个Label来显示16个方块
        jLabels = new JLabel[16];
        JLabel j;
        for (int i = 0; i < 16; i++) {
            jLabels[i] = new JLabel("0",JLabel.CENTER);
            j = jLabels[i];
            j.setOpaque(true);
            j.setBorder(BorderFactory.createMatteBorder(6,6,6,6,new java.awt.Color(0xBBADA0)));

            j.setFont(new Font("Dialog",1,52));
            mainJFrame.add(j);
        }
        mainJFrame.setVisible(true);
    }
    //向左移动
    private void left(int arr[]){
        moveLeft(arr);
        combineLeft(arr);
        moveLeft(arr);//合并完之后会再次产生空位，要再次左移
    }
    //向左合并方块
    private void combineLeft(int arr[]){
        for (int l = 0; l < 4; l++) {
            for (int i = 0; i < 3; i++) {
                if ((arr[l*4+i] != 0)&&(arr[l*4+1] != 0)&&(arr[l*4+i] == arr[l*4+i+1])){
                    arr[l*4+i] *= 2;
                    arr[l*4+i+1] = 0;
                }
            }
        }
    }
    //方块左移，，逐行扫描，利用临时数组实现左移、
    private void moveLeft(int arr[]){
        for (int l = 0; l < 4; l++) {
            int z = 0, fz = 0;//z(零),fz(非零)
            for (int i = 0; i < 4; i++) {
                if(arr[l*4+i] == 0){
                    z++;
                }else {
                    temp[fz] = arr[l*4+i];
                    fz++;
                }
            }
            for (int j = fz; j < 4; j++) {
                temp[j] = 0;
            }
            for (int i = 0; i < 4; i++) {
                arr[l*4+i] = temp[i];
            }
        }
    }
    private void moveRight(int arr[]){
        for (int l = 0; l < 4; l++) {
            int z = 3,fz = 3;
            for (int i = 3; i >= 0; i--) {
                if (arr[l*4+i] == 0){
                    z--;
                }else {
                    temp[fz] = arr[l*4+i];
                    fz--;
                }
            }
            for (int i = fz; i >= 0; i--) {
                temp[i] = 0;
            }
            for (int j = 3; j >= 0; j--) {
                arr[l*4+j] = temp[j];
            }
        }
    }
    private void combineRight(int arr[]){
        for (int l = 0; l < 4; l++) {
            for (int i = 3; i > 0; i--) {
                if ((arr[l*4+i] != 0 && arr[l*4+i-1] != 0 && arr[l*4+i] ==arr[l*4+i-1])){
                    arr[l*4+i] *= 2;
                    arr[l*4+i-1] = 0;
                }
            }
        }
    }
    private void right(int arr[]){
        moveRight(arr);
        combineRight(arr);
        moveRight(arr);
    }

    private void moveUp(int arr[]){
        for (int l = 0; l < 4; l++) {
            int z = 0, fz = 0;
            for (int i = 0; i < 4; i++) {
                if (arr[l+4*i] == 0){
                    z++;
                }else {
                    temp[fz] = arr[l+4*i];
                    fz++;
                }
            }
            for (int i = fz; i < 4; i++) {
                temp[i] = 0;
            }
            for (int j = 0; j < 4; j++) {
                arr[l+4*j] = temp[j];
            }
        }
    }
    private void combineUp(int arr[]){
        for (int l = 0; l < 4; l++) {
            for (int i = 0; i < 3; i++) {
                if ((arr[l+4*i] != 0 && arr[l+4*(i+1)] != 0 && arr[l+4*i] == arr[l+4*(i+1)])){
                    arr[l+4*i] *= 2;
                    arr[l+4*(i+1)] = 0;
                }
            }
        }
    }
    private void up(int arr[]){
        moveUp(arr);
        combineUp(arr);
        moveUp(arr);
    }

    private void moveDown(int arr[]){
        for (int l = 0; l < 4; l++) {
            int z =3,fz = 3;
            for (int i = 3; i >= 0; i--) {
                if (arr[l+4*i] == 0){
                    z--;
                }else {
                    temp[fz] = arr[l+4*i];
                    fz--;
                }
            }
            for (int i = fz; i >= 0; i--) {
                temp[i] = 0;
            }
            for (int j = 3; j >= 0; j--) {
                arr[l+4*j] = temp[j];
            }
        }
    }
    private void combineDown(int arr[]){
        for (int l = 0; l < 4; l++) {
            for (int i = 3; i > 0; i--) {
                if ((arr[l+4*i] != 0 && arr[l+4*(i-1)] != 0 && arr[l+4*i] == arr[l+4*(i-1)])){
                    arr[l+4*i] *= 2;
                    arr[l+4*(i-1)] = 0;
                }
            }
        }
    }
    private void down(int arr[]){
        moveDown(arr);
        combineDown(arr);
        moveDown(arr);
    }

}
