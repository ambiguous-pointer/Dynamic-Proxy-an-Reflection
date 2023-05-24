package org.example.sockert;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        try {
            // 创建 DatagramSocket 对象，并指定监听的端口号
            DatagramSocket serverSocket = new DatagramSocket(12345);

            // 接收客户端的请求
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            serverSocket.receive(receivePacket);

            // 处理客户端的请求
            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("接收到客户端的消息：" + message);

            // 发送响应给客户端
            byte[] sendBuffer = "Hello, Client!".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
                    receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);

            // 关闭连接
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}