package com.xiaokai.threadtest.lesson02;

import java.util.ArrayList;

/**
 * 线程不安全的集合
 */
public class UnsafeList {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 100000; i++) {
            new Thread(()->{
                synchronized (arrayList){
                    arrayList.add(Thread.currentThread().getName());
                }

            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(arrayList.size());
    }
}
