package com.xiaokai.threadtest.lesson03;

/**
 * 线程通讯，生产者消费者模式：管程法
 */
public class TestPC {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new Producer(buffer).start();
        new Consumer(buffer).start();
    }
}
//产品
class Chicken {
    int ID;

    public Chicken(int ID) {
        this.ID = ID;
    }
}
//生产者
class Producer extends Thread{
    Buffer buffer;
    public Producer(Buffer buffer){
        this.buffer = buffer;
    }
    //生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                buffer.push(new Chicken(i));
                System.out.println("生产了第"+i+"只鸡");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//消费者
class Consumer extends Thread{
    Buffer buffer;
    public Consumer(Buffer buffer){
        this.buffer = buffer;
    }
    //消费
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                buffer.pop();
                System.out.println("消费了第"+buffer.pop().ID+"只鸡");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//缓冲区
class Buffer extends Thread{
    //缓冲区大小
    Chicken[] chickens = new Chicken[10];
    //计数器
    int offset = 0;

    //构造方法
    public Buffer(){

    }
    //生产方法
    public synchronized void push(Chicken chicken) throws InterruptedException {
        //如果缓冲区满了，就要通知消费者消费，自己wait
        if (offset == chickens.length){
            wait();
        }
        //没有满将chicken放入缓冲区
        chickens[offset] = chicken;
        offset++;
        //可以通知消费者消费
        notifyAll();
    }
    //消费方法
    public synchronized Chicken pop() throws InterruptedException {
    //如果缓冲区是空的就要wait等待生产者生成产品
        if (offset == 0){
            wait();
        }
        //没有空的话，取走缓冲区的chicken，
        offset--;
        Chicken chicken = chickens[offset];


        //可以通知生产者生产了
        notifyAll();
        return chicken;
    }
}

