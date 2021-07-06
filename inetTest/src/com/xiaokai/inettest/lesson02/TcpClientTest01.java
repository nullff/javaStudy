package com.xiaokai.inettest.lesson02;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClientTest01 {
    public static void main(String[] args) {
        InetAddress serverIP = null;
        Socket socket = null;
        OutputStream os = null;

        try {
            //1.新建socket,设定端口、
            serverIP = InetAddress.getByName("127.0.0.1");
            int port = 9999;
            //2.创建socket
            socket = new Socket(serverIP, port);
            //3.创建输出流
            os = socket.getOutputStream();
            os.write("这里是客户端，发送测试！".getBytes());//将string字节序列化



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //4.关闭资源
            try {
                if (os!=null)os.close();
                if (socket!=null)socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
