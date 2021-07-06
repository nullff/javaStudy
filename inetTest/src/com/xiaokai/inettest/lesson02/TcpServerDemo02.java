package com.xiaokai.inettest.lesson02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo02 {
    public static void main(String[] args) throws Exception {
        //开放端口
        ServerSocket serverSocket = new ServerSocket(9000);
        //等待链接
        Socket accept = serverSocket.accept();
        //接收文件
        InputStream is = accept.getInputStream();
        //处理文件
        FileOutputStream fos = new FileOutputStream(new File("recive.png"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read()) != -1){
            fos.write(buffer,0,len);
        }

        //通知客户端接收完毕
        OutputStream os = accept.getOutputStream();
        os.write("接收完毕！".getBytes());

        //关闭资源
        os.close();
        fos.close();
        is.close();
        accept.close();
        serverSocket.close();
    }
}
