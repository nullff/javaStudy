package com.xiaokai.annotation.lesson01;
//测试ORM：对象关系映射

import java.lang.annotation.*;
import java.lang.reflect.Field;

//反射读取注解信息三部
//1.定义注解
//2.在类中使用注解
//3.在反射中获取注解
public class Test04 {
    public static void main(String[] args) {

        try {
            //反射，可以获得类的全部信息，所有的东西
            Class aClass = Class.forName("com.xiaokai.annotation.lesson01.Student");

            //获得这个类的注解
            Annotation[] annotations = aClass.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }

            //获得类的注解的值
            TableName table = (TableName) aClass.getAnnotation(TableName.class);
            System.out.println(table.value());

            //获得类指定的注解的值
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field);
            }

            Field name = aClass.getDeclaredField("name");
            FieldName fieldName = name.getAnnotation(FieldName.class);
            System.out.println(fieldName.columnName()+"-->"+fieldName.type()+"-->"+fieldName.length());
            //我们可以根据得到的类的信息，通过JDBC生成相关的额SQL语句，执行就可以动态生成数据库表


        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

@TableName("db_student")
class Student{
    @FieldName(columnName = "db_id",type = "int",length = 10)
    private int id;
    @FieldName(columnName = "db_name",type = "varchar",length = 10)
    private String name;
    @FieldName(columnName = "db_age",type = "int",length = 3)
    private int age;

    public Student() {
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


//表名注解，只有一个参数，使用value命名；
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableName{
    String value();
}

//属性注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldName{
    String columnName();//列名
    String type();//类型
    int length();//长度
}