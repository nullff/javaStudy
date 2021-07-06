package com.xiaokai.inettest.lesson02;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端发消息给服务端，服务端将消息显示在控制台上
 */
public class TcpClientDemo01 {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream os = null;

        //1.链接服务器
        try {
            InetAddress serverIP = InetAddress.getByName("127.0.0.1");
            int port = 8899;
            //2.创建一个socket
            socket = new Socket(serverIP,port);
            //3.创建一个输出流，向外写东西
            os = socket.getOutputStream();
            os.write("你好，这里是客户端！".getBytes());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (IOException e1){
            e1.printStackTrace();
        }finally {
            //4.关闭资源
            try {
                if (os != null){
                    os.close();
                }
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
