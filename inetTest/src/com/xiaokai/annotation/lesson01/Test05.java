package com.xiaokai.annotation.lesson01;

public class Test05 {
    public static void main(String[] args) {

        try {
            //通过反射获取类的Class
            Class c1 = Class.forName("com.xiaokai.annotation.lesson01.User");
            //一个类被加载后，整个类的结构信息就会放到对应的Class对象中；
            System.out.println(c1);

            //一个类只有一个Class对象
            Class c2 = Class.forName("com.xiaokai.annotation.lesson01.User");
            System.out.println(c1.hashCode());
            System.out.println(c2.hashCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
class User{
    private int id;
    private int age;
    private String name;

    public User() {}

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}