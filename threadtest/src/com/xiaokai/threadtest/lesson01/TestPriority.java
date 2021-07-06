package com.xiaokai.threadtest.lesson01;

public class TestPriority {
    public static void main(String[] args) {
        MyPriority priority = new MyPriority();
        new Thread(priority,"t1").start();
        Thread t2 = new Thread(priority,"t2");
        t2.setPriority(1);
        t2.start();

        Thread t3 = new Thread(priority,"t3");
        t3.setPriority(Thread.MAX_PRIORITY);
        t3.start();
        new Thread(priority,"t4").start();
        new Thread(priority,"t5").start();


    }

}
class MyPriority implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"---->"+Thread.currentThread().getPriority());
    }
}
