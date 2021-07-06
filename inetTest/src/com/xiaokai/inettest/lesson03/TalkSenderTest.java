package com.xiaokai.inettest.lesson03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TalkSenderTest implements Runnable{
    private DatagramSocket socket = null;
    private BufferedReader bufferedReader = null;
    private int srcPort;
    private String desIP;
    private int desPort;

    public TalkSenderTest(int srcPort, String desIP, int desPort) {
        this.srcPort = srcPort;
        this.desIP = desIP;
        this.desPort = desPort;
    }

    @Override
    public void run() {
        try{
            System.out.println("发送方启动中。。。");
            socket = new DatagramSocket(srcPort);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                String data = bufferedReader.readLine();
                byte [] senddatas = data.getBytes();
                DatagramPacket packet = new DatagramPacket(senddatas,0,senddatas.length,new InetSocketAddress(desIP,desPort));
                socket.send(packet);
                if (data.equals("BYE")){
                    break;
                }
            }
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
