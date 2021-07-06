package com.xiaokai.gametest.test2048;

import com.xiaokai.lesson03.JListTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame {
    JFrame mainFrame;
    final String gameRule = "2048游戏公有16个格子，开始时会生成两个随机的数值为2和4的方块，\n"+
                            "玩家可以通过上、下、左、右方向键来操控方块的滑动方向，\n"+
                            "每按一次方向键，所有的方块会向一个方向靠拢，相同数值的方块将会相加并合成一个方块，\n"+
                            "此外，每滑动一次将随机生成一个数字为2或者4的方块，\n"+
                            "玩家需要想办法在这16个格子上凑出2048数值的方块，若16个格子被填满且无法再移动，\n"+
                            "则游戏结束。";
    public StartFrame(){
        initFrame();
    }
    private void initFrame(){
        mainFrame = new JFrame("2048 Game");
        mainFrame.setSize(500,500);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);//窗口居中

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        //BoxLayout.Y_AXIS是指从上到下垂直布置组件
        jPanel.add(newLine(Box.createVerticalStrut(25)));//添加空白区域
        JLabel jLabel = new JLabel("2048");
        jLabel.setForeground(new Color(0x776e65));
        jLabel.setFont(new Font("Dialog",1,92));
        jPanel.add(newLine(jLabel));

        jPanel.add(newLine(Box.createVerticalStrut(50)));

        JButton btn1 = new JButton("开始游戏");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game();
                mainFrame.dispose();
            }
        });
        jPanel.add(newLine(btn1));

        jPanel.add(newLine(Box.createVerticalStrut(50)));

        JButton btn2 = new JButton("游戏规则");
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,gameRule,"游戏规则",JOptionPane.PLAIN_MESSAGE);
            }
        });
        jPanel.add(newLine(btn2));
        jPanel.add(newLine(Box.createVerticalStrut(50)));

        JButton btn3 = new JButton("退出游戏");
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(0);
            }
        });
        jPanel.add(newLine(btn3));

        mainFrame.add(jPanel);
        mainFrame.setVisible(true);
    }

    //添加新一行垂直居中的空间，通过在空间两边填充glue对象实现
    private JPanel newLine(Component c){
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp,BoxLayout.X_AXIS));
        jp.add(Box.createHorizontalGlue());
        jp.add(c);
        jp.add(Box.createHorizontalGlue());
        jp.setOpaque(true);//设置不透明

        return jp;

    }
}
