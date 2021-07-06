package com.xiaokai.threadtest.lesson01;

public class TestProxy {
    public static void main(String[] args) {
        Marry marry = new MarryCompany(new Who());
        marry.HappyMarry();
    }
}
interface Marry{
    void HappyMarry();
}
class Who implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("结婚了，开心");
    }
}
class MarryCompany implements Marry{
    private Who name;

    public MarryCompany(Who name) {
        this.name = name;
    }

    @Override
    public void HappyMarry() {
        beforeMarry();
        name.HappyMarry();
        afterMarry();

    }
    private void beforeMarry(){
        System.out.println("婚前紧张");
    }
    private void afterMarry(){
        System.out.println("婚后不紧张");
    }
}
