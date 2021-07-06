package com.xiaokai.threadtest.lesson01;

public class TestThread1 implements Runnable{
    int num = 100;
    @Override
    public void run() {
        while (true){
            if (num <= 0){
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(Thread.currentThread().getName()+"取到了第"+num--+"张票");
        }



    }

    public static void main(String[] args) {
        TestThread1 thread1 = new TestThread1();
        //不同的线程对同一资源出现线程争用！！！
        new Thread(thread1,"小明").start();
        new Thread(thread1,"老师").start();
        new Thread(thread1,"黄牛").start();
    }
}
