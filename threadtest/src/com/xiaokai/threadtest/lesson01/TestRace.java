package com.xiaokai.threadtest.lesson01;

public class TestRace implements Runnable{
    private String winner;
    private boolean isWin;
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (Thread.currentThread().getName()=="兔子" && i > 10 && i%10==0){
                try {
                    Thread.sleep(10);
                    System.out.println("兔子睡了10ms");
                } catch (InterruptedException e) {
                    System.out.println("出现异常"+e);
                }
            }

            if (gameOver(i)){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"已经跑了"+i+"步，");


        }
    }
    private boolean gameOver(int step){
        if (winner != null){
            return true;
        }else if(step == 100){
            winner = Thread.currentThread().getName();
            System.out.println("冠军是："+winner);
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        TestRace race = new TestRace();
        new Thread(race,"乌龟").start();
        new Thread(race,"兔子").start();

    }
}
