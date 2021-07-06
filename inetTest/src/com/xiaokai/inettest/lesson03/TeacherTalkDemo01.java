package com.xiaokai.inettest.lesson03;

public class TeacherTalkDemo01 {
    public static void main(String[] args) {
        new Thread(new TalkSenderTest(8999,"localhost",8000)).start();
        new Thread(new TalkReceiveTest(9090,"student")).start();
    }
}
