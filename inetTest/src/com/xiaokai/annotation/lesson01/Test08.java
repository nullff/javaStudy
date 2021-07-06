package com.xiaokai.annotation.lesson01;

import java.lang.annotation.*;

//练习ORM，反射操作注解
public class Test08 {
    public static void main(String[] args) {
        try {
            Class<?> c1 = Class.forName("com.xiaokai.annotation.lesson01.Student3");
            Annotation[] annotations = c1.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

@TableGuan("db_student")
class Student3{
    @FieldGuan(columnName = "db_id",type = "int",length = 10)
    private int id;
    @FieldGuan(columnName = "db_age",type = "int",length = 10)
    private int age;
    @FieldGuan(columnName = "db_name",type = "varchar",length = 10)
    private String name;

    public Student3() {
    }

    public Student3(int id, int age, String name) {
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
        return "Student3{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

//类名注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableGuan{
    String value();
}

//属性注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldGuan{
    String columnName();
    String type();
    int length();
}
