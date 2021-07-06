package com.xiaokai.annotation.lesson01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//测试自定义注解
public class Test03 {
    //显式定义值，不显式就是默认default；
    @MyAnnotation2(name = "xiaokai",age = 25,id = 1,schools = {"HBUT"})
    public void test(){}


    //只有一个参数，默认名字一般是value，可以省略不写；
    @MyAnnotation3("aaa")
    public void test2(){}

}

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    //参数类型  参数名
    String name() default "";
    int age() default 0;
    int id() default -1;
    String [] schools() default {"测试","学校"};

}


@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    String value();
}