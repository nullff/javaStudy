package com.xiaokai.threadtest.lesson02;

public class UnSafeBank {
    public static void main(String[] args) {
        Account account = new Account("存款", 1000);
        Drawing you = new Drawing("you", account, 500);
        Drawing me = new Drawing("me", account, 1000);

        you.start();me.start();

    }
}
class Account{
    public String name;
    public int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }
}
class Drawing extends Thread{
    private String name;
    private Account account;
    private int drawingMoney,nowMoney;

    public Drawing(String name, Account account, int drawingMoney) {
        super(name);
        this.drawingMoney = drawingMoney;
        this.account = account;
    }

    @Override
    public void run() {
        synchronized (account){
            if (account.money - drawingMoney < 0){
                System.out.println(Thread.currentThread().getName()+"钱不够，取不出来");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money -= drawingMoney;
            nowMoney += drawingMoney;
            System.out.println(account.name+"账户余额"+account.money);
            System.out.println(Thread.currentThread().getName()+"现在手里有"+nowMoney);
        }

    }
}
