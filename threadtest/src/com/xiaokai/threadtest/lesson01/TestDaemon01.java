package com.xiaokai.threadtest.lesson01;

public class TestDaemon01 {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);
        thread.start();

        new Thread(you).start();
    }
}
class God implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("守护着你");
        }
    }
}
class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 30000; i++) {
            System.out.println("开心的活着！");
        }
        System.out.println("=====GoodBye!=====");
    }
}
