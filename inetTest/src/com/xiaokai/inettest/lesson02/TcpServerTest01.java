package com.xiaokai.inettest.lesson02;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerTest01 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;

        try {

            //1.开启端口
            serverSocket = new ServerSocket(9999);
            System.out.println("这里是服务端，等待接受：");
            //2.等待链接
            while (true){
                accept = serverSocket.accept();
                //3.接收client发送来的数据,读取消息
                is = accept.getInputStream();

                //4.处理消息
                //使用管道避免中文乱码
                baos = new ByteArrayOutputStream();
                //创建缓冲区大小1024个字节
                byte [] buffer = new byte[1024];
                //数据长度计数器
                int len;
                //read数据，将其通写入缓冲区；之后使用write将buffer中0开始的len个字节输出到输出流
                while ((len = is.read(buffer)) != -1){
                    baos.write(buffer,0,len);
                }
                System.out.println(baos.toString());//输出流转换成字符
                System.out.println("数据来源：" + accept.getInetAddress().getHostName());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //依次关闭资源
            try {
                if (baos!=null) baos.close();
                if (is!=null)is.close();
                if (accept!=null)accept.close();
                if (serverSocket!=null)serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
