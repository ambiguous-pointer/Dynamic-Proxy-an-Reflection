package org.example.sockert;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try {
            // 创建 DatagramSocket 对象
            DatagramSocket clientSocket = new DatagramSocket();

            // 构造要发送的数据
            String message = "Hello, Server!";
            byte[] sendBuffer = message.getBytes();

            // 发送数据给服务器
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            // 接收服务器的响应
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);

            // 处理服务器的响应
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("接收到服务器的响应：" + response);

            // 关闭连接
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
