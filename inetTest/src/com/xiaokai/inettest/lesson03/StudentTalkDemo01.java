package com.xiaokai.inettest.lesson03;

public class StudentTalkDemo01 {
    public static void main(String[] args) {
        new Thread(new TalkSenderTest(9000,"localhost",9090)).start();
        new Thread(new TalkReceiveTest(8000,"teacher")).start();
    }
}
