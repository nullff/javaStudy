package com.xiaokai.inettest.lesson03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpSenderDemo01 {
    public static void main(String[] args) throws Exception {
        System.out.println("发送方启动中。。。");
        DatagramSocket socket = new DatagramSocket(9000);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String data = bufferedReader.readLine();
            byte [] senddatas = data.getBytes();
            DatagramPacket packet = new DatagramPacket(senddatas,0,senddatas.length,new InetSocketAddress("localhost",9090));
            socket.send(packet);
            if (data.equals("BYE")){
                break;
            }
        }
        socket.close();
    }
}
