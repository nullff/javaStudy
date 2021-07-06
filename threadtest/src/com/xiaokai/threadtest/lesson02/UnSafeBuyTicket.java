package com.xiaokai.threadtest.lesson02;

public class UnSafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket ticket = new BuyTicket();
        new Thread(ticket,"小明").start();
        new Thread(ticket,"我").start();
        new Thread(ticket,"黄牛").start();

    }
}
class BuyTicket implements Runnable{
    private int num = 1000;
    private boolean flag = true;
    @Override
    public void run() {
        while (flag){
            buy();
        }

    }
    public synchronized void buy(){
        if (num <= 0 ){
            flag = false;
            return;
        }
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread().getName()+"拿到了第"+ num-- +"张票");
    }
}