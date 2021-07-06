package com.xiaokai.inettest.lesson03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServerDemo01 {
    public static void main(String[] args) throws Exception {
        //开放端口
        DatagramSocket socket = new DatagramSocket(9000);
        //接受数据
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

        socket.receive(packet);

        //3.输出数据
        System.out.println(packet.getAddress().getHostName()+"："+new String(packet.getData(),0, packet.getLength()));
        //guanbi
        socket.close();

    }
}
