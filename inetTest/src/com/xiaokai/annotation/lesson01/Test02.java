package com.xiaokai.annotation.lesson01;

import java.lang.annotation.*;

//测试元注解
public class Test02 {
    @MyAnnotation
    public void test2(){

    }

}
//定义一个注解
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Documented
@interface MyAnnotation{
    //测试作用域，了解@Retention 概念
}