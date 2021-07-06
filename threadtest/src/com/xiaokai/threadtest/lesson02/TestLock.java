package com.xiaokai.threadtest.lesson02;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        BuyTicket2 ticket2 = new BuyTicket2();
        new Thread(ticket2,"小明").start();
        new Thread(ticket2,"黄牛党").start();
    }
}
class BuyTicket2 implements Runnable{
    int num = 10;
    @Override
    public void run() {
        while (num > 0){
            buy();
        }

    }
    private void buy(){
        final ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {

        if (num <= 0){
            System.out.println("票没有了");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            System.out.println(Thread.currentThread().getName()+"拿到了第"+ num-- +"张票");
        } finally {
            lock.unlock();
        }
    }
}
