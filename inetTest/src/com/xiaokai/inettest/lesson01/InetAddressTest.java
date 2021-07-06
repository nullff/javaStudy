package com.xiaokai.inettest.lesson01;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) {

        try {
            //获得IP地址
            InetAddress inetAddress1 = InetAddress.getByName("192.168.17.1");
            System.out.println(inetAddress1);
            InetAddress inetAddress2 = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddress2);

            //获得本机IP
            InetAddress inetAddress3 = InetAddress.getByName("127.0.0.1");
            InetAddress inetAddress4 = InetAddress.getByName("localhost");
            System.out.println(inetAddress3);
            System.out.println(inetAddress4);

            //getHostName
            System.out.println(inetAddress2.getHostName());
            //getHostAddress
            System.out.println(inetAddress2.getHostAddress());
            //Canonical规范的
            System.out.println(inetAddress2.getCanonicalHostName());


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
