package com.xiaokai.annotation.lesson01;

import java.util.ArrayList;
import java.util.List;

//测试内置注解
//所有类摸摸人继承Object
public class Test01 extends Object{
    @Override//表示方法重写
    public String toString() {
        return super.toString();
    }
    @Deprecated
    public static void stop(){
        System.out.println("测试stop @Deprecated");
    }
    @SuppressWarnings("all")//抑制警告 可以传参数
    public void sw(){
        List list = new ArrayList();
    }

    public static void main(String[] args) {
        stop();
        // STOPSHIP: 2021/6/20  
        
    }
}
