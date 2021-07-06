package com.xiaokai.inettest.lesson03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpReceiveDemo01 {
    public static void main(String[] args) throws Exception {
        System.out.println("接收方启动中。。。");
        DatagramSocket socket = new DatagramSocket(9090);
        while (true){
            byte [] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
            socket.receive(packet);

            byte[] receivedatas = packet.getData();
            String data = new String(receivedatas, 0, packet.getLength());
            System.out.println(packet.getAddress().getHostName()+data);
            if (data.equals("BYE"))break;
        }


        socket.close();
    }
}
