package com.xiaokai.inettest.lesson01;

import java.net.InetSocketAddress;

public class InetSocketAddressTest {
    public static void main(String[] args) {
        java.net.InetSocketAddress inetSocketAddress1 = new java.net.InetSocketAddress("127.0.0.1",8080);
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress("localhost",9000);

        System.out.println(inetSocketAddress1.getHostName());
        System.out.println(inetSocketAddress1.getAddress());
        System.out.println(inetSocketAddress1.getPort());

        System.out.println(inetSocketAddress2.getHostName());
        System.out.println(inetSocketAddress2.getAddress());//返回地址
        System.out.println(inetSocketAddress2.getPort());//返回端口
    }
}
