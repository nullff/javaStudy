package com.xiaokai.annotation.lesson01;
//测试各种获得Class类型的方式
public class Test06 {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student2();
        System.out.println("这个人是"+person.name);

        //通过对象获得
        Class c1 = person.getClass();

        //通过字符串获得
        Class c2 = Class.forName("com.xiaokai.annotation.lesson01.Student2");

        //通过类的静态成员class获得
        Class c3 = Person.class;

        //只针对内部的基本数据类型
        Class c4 = Integer.TYPE;

        //获得父类的类型
        Class c5 = c2.getSuperclass();

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);


    }
}

class Person{
    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student2 extends Person{
    public Student2() {
        this.name = "xiaokai";
    }
}

class Teacher extends Person{
    public Teacher() {
        this.name = "小凯";
    }
}