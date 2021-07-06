package com.xiaokai.inettest.lesson02;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClientDemo02 {
    public static void main(String[] args) throws Exception {
        //创建链接
        Socket serverIP = new Socket(InetAddress.getByName("127.0.0.1"),9000);
        //创建输出流
        OutputStream os = serverIP.getOutputStream();
        FileInputStream fis = new FileInputStream(new File("D:\\code\\inetTest\\src\\com\\xiaokai\\inettest\\lesson02\\send.jpg"));
        //写出文件
        byte[] buffer = new byte[1024];
        int len;
        while ((len=fis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        //通知服务端写完毕
        serverIP.shutdownOutput();
        //接收服务端的确认信息
        InputStream is = serverIP.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte [] buffer2 = new byte[1024];
        int len2;
        while ((len2=is.read(buffer2)) != -1){
            baos.write(buffer2,0,len2);
        }
        System.out.println(baos.toString());
        //关闭资源
        fis.close();
        os.close();
        serverIP.close();
    }
}
