package com.xiaokai.threadtest.lesson02;

public class TestDeadlock {
    public static void main(String[] args) {
        MakeUp m1 = new MakeUp("小明",0);
        MakeUp m2 = new MakeUp("小红",1);
        m1.start();
        m2.start();
    }
}
class Lipstick{

}
class Mirror{

}
class MakeUp extends Thread {
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();
    String name;
    int flag = 0;

    public MakeUp(String name,int flag) {
        this.name = name;
        this.flag = flag;

    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void makeup() throws InterruptedException {
        if (flag == 0){
            synchronized (lipstick){
                System.out.println(this.name+"拿到了口红");
                Thread.sleep(1000);

            }
            synchronized (mirror){
                System.out.println(this.name+"拿到了镜子");
            }
        }else {
            synchronized (mirror){
                System.out.println(this.name+"拿到了镜子");
                Thread.sleep(1000);
            }
            synchronized (lipstick){
                System.out.println(this.name+"拿到了口红");
            }
        }
    }
}