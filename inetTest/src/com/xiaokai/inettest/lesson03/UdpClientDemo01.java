package com.xiaokai.inettest.lesson03;

import java.net.*;

public class UdpClientDemo01 {
    public static void main(String[] args) throws Exception {
        //新建链接
        DatagramSocket socket = new DatagramSocket();
        //2.封装数据包
        String msg = "发送测试！";
        byte[] data = msg.getBytes();
        int port = 9000;
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramPacket packet = new DatagramPacket(data, 0, data.length, address, port);

        //3.通过socket发送
        socket.send(packet);

        //4.关闭资源
        socket.close();
    }
}
