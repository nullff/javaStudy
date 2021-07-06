package com.xiaokai.inettest.lesson03;

import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo01 {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/helloworld/index.jsp?username=xiaokai&&password=123");
        System.out.println(url.getProtocol());//协议名
        System.out.println(url.getHost());//主机名
        System.out.println(url.getPort());
        System.out.println(url.getPath());//文件路径
        System.out.println(url.getFile());//文件名
        System.out.println(url.getQuery());//查询条件
    }
}
