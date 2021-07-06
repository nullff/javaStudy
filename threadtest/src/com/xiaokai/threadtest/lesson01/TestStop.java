package com.xiaokai.threadtest.lesson01;

public class TestStop implements Runnable{
    private boolean flag = true;
    private int i ;
    public static void main(String[] args) throws InterruptedException {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i <= 1000; i++) {
            if (i == 100) {
                testStop.stop();
                System.out.println("线程停止"+i);}
            Thread.sleep(10);
        }
    }

    @Override
    public void run() {
        while (flag){
            System.out.println("刷新"+i++);
        }
    }
    private void stop(){
        flag = false;
    }
}
