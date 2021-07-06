package com.xiaokai.threadtest.lesson01;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSleep2 {
    private void printNow() throws InterruptedException {
        Date startTime = new Date(System.currentTimeMillis());
        while (true){
            Thread.sleep(1000);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
            startTime = new Date(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TestSleep2().printNow();
    }

}
