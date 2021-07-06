package com.xiaokai.threadtest.lesson01;

public class StartThread extends Thread{
    @Override
    public void run() {
        //线程体
        for (int i = 0; i < 2000; i++) {
            System.out.println("线程体==>"+i);
        }
    }
    //主程序入口
    public static void main(String[] args) {
        StartThread thread = new StartThread();
        thread.start();
        for (int i = 0; i < 2000; i++) {
            System.out.println("这里是主方法入口==>"+i);
        }
    }
}

