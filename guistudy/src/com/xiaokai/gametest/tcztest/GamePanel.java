package com.xiaokai.gametest.tcztest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    Data data = new Data();
    private int length;//长度
    private  int [] snakeX = new int[50];//横坐标
    private  int [] snakeY = new int[50];//纵坐标
    private String fx = "R";//猪头朝向
    //食物
    Random random = new Random();
    private int foodx,foody;
    //状态
    private boolean isStart = false;
    private boolean isFail = false;
    private int score = 0;
    //定时器
    Timer timer = new Timer(200,this);



    public GamePanel(){
        init();
        //读取焦点事件
        this.setFocusable(true);
        //添加键盘监控事件
        this.addKeyListener(this);
        timer.start();
    }
    //初始化操作
    private void init(){
        length = 3;//刚开始长度为3
        //初始定位
        snakeX[0] = 150;snakeY[0] = 150;
        snakeX[1] = 125;snakeY[1] = 150;
        snakeX[2] = 100;snakeY[2] = 150;

        //初始化食物
        foodx = 25+25*random.nextInt(34);
        foody = 75+25*random.nextInt(24);



    }
    //根据初始化操作绘制相关组件

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        this.setBackground(Color.white);//设置面板背景色
        data.header.paintIcon(this,g,25,11);//广告位
        g.fillRect(25,75,850,600);
        //画静态结构
        switch (fx){
            case "R":
                data.right.paintIcon(this,g,snakeX[0],snakeY[0] );
                break;
            case "L":
                data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
                break;
            case "U":
                data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
                break;
            case "D":
                data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
                break;
        }
        //画身体，身体length控制，每次遍历即可
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i] );
        }
        //画食物
        data.food.paintIcon(this,g,foodx,foody);
        //提示信息
        if (!isStart){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏！",300,300);
        }
        //失败信息
        if (isFail){
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,30));
            g.drawString("失败了~按下空格重新开始！",200,300);
        }
        //显示成绩
        g.setColor(Color.CYAN);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度："+length,750,35);
        g.drawString("分数："+score,750,50);

    }

    @Override
    //定时器事件
    public void actionPerformed(ActionEvent e) {
        //如果isStart是真，且没有结束，则可以开始移动
        if (isStart && isFail==false){
            //身子移动，除了脑袋其他的所有节点都是移动到前一个节点的位置
            for (int i = length-1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //头的移动，根据上下左右选择
            switch (fx){
                case "R":
                    snakeX[0] = snakeX[0] + 25;
                    if (snakeX[0] > 850) snakeX[0] = 25;
                    break;
                case "L":
                    snakeX[0] = snakeX[0] - 25;
                    if (snakeX[0] < 25) snakeX[0] = 850;
                    break;
                case "U":
                    snakeY[0] = snakeY[0] - 25;
                    if (snakeY[0] < 75) snakeY[0] = 650;
                    break;
                case "D":
                    snakeY[0] = snakeY[0] + 25;
                    if (snakeY[0] > 650) snakeY[0] = 75;
                    break;
            }
            //判断吃到东西
            if (snakeX[0] == foodx && snakeY[0] == foody){
                //长度加一
                length++;
                //增加成绩
                score+=10;
                //重新生成食物
                foodx = 25+25*random.nextInt(34);
                foody = 75+25*random.nextInt(24);
            }
            //判断失败，只要头和身体任何一节相等，说明失败
            for (int i = 1; i < length; i++) {
                if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]){
                    isFail = true;
                }
            }
            repaint();//每一次判断后都重画
        }
        timer.start();//定时器
    }



    @Override
    //键盘监听事件
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        //键盘控制状态
        if (keyCode == KeyEvent.VK_SPACE){
            if (isFail){
                isFail = false;
                init();
            }else {
                isStart = !isStart;
            }
            repaint();
        }

        //键盘控制方向
        switch (keyCode){
            case KeyEvent.VK_UP:
                fx = "U";
                break;
            case KeyEvent.VK_DOWN:
                fx = "D";
                break;
            case KeyEvent.VK_LEFT:
                fx = "L";
                break;
            case KeyEvent.VK_RIGHT:
                fx = "R";
                break;
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
