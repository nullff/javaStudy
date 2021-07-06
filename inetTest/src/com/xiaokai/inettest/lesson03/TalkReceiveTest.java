package com.xiaokai.inettest.lesson03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TalkReceiveTest implements Runnable{
    private DatagramSocket socket = null;
    private DatagramPacket packet = null;
    int desPort ;
    String name;

    public TalkReceiveTest(int desPort, String name) {
        this.name = name;
        this.desPort = desPort;

    }

    @Override
    public void run() {
        try {
            System.out.println("接收方启动中。。。");
            socket = new DatagramSocket(desPort);
            while (true){
                byte [] buffer = new byte[1024];
                packet = new DatagramPacket(buffer,0,buffer.length);
                socket.receive(packet);

                byte[] receivedatas = packet.getData();
                String data = new String(receivedatas, 0, packet.getLength());
                System.out.println(packet.getAddress().getHostName()+name+"："+data );
                if (data.equals("BYE"))break;
            }


            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
