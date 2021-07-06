package com.xiaokai.inettest.lesson02;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo01 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;

        try {
            //1.开放服务端口，创建serversocket
            serverSocket = new ServerSocket(8899);
            //2.等待客户端链接
            accept = serverSocket.accept();
            //3.读入客户端消息
            is = accept.getInputStream();

            baos = new ByteArrayOutputStream();//防止中文乱码
            byte [] buffer = new byte[1024];
            int len;
            while ((len=is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }
            System.out.println(baos.toString());
            System.out.println("数据来源"+accept.getInetAddress().getHostName());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //4.关闭资源
            try {
                if (baos != null){
                    baos.close();
                }
                if (is != null){
                    is.close();
                }
                if (accept != null){
                    accept.close();
                }
                if (serverSocket != null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
