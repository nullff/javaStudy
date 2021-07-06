package com.xiaokai.threadtest.lesson03;
/**
 * 测试生产者消费者问题 ：信号灯法
 */
public class TestPC2 {
    public static void main(String[] args) {
        SMovie movie = new SMovie();
        new Player(movie).start();
        new Watcher(movie).start();
    }
}
//生产者--演员
class Player extends Thread{
    SMovie movie;
    public Player(SMovie movie){
        this.movie = movie;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            this.movie.play("觉醒年代"+i);
        }
    }
}
//消费者--观众
class Watcher extends Thread{
    SMovie movie;
    public Watcher(SMovie movie){
        this.movie = movie;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            this.movie.watch();
        }
    }
}
//产品--电影
class SMovie extends Thread{
    //信号灯标志位
    boolean flag = true;
    public String movie;
//    public SMovie(String movie){
//        this.movie = movie;
//    }

    //生产方法
    public synchronized void play(String movie){
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("正在放映："+movie);
        this.flag = !flag;//信号灯操作反转
        this.movie = movie;
        //通知观众观看
        notifyAll();
    }
    //消费方法
    public synchronized void watch(){
        //正在制作的电影的话无法观看
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看了："+movie);
        this.flag = !flag;//信号灯操作反转
        //通知演员制作电影
        notifyAll();
    }
}
