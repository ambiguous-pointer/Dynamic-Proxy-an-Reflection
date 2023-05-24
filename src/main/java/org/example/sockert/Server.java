package org.example.sockert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // 创建 ServerSocket 对象，并指定监听的端口号
            ServerSocket serverSocket = new ServerSocket(12345);

            // 监听客户端连接
            System.out.println("等待客户端连接...");
            Socket clientSocket = serverSocket.accept();

            // 获取输入流和输出流
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            // 读取客户端发送的数据
            byte[] buffer = new byte[1024];
            int length = inputStream.read(buffer);
            String message = new String(buffer, 0, length);
            System.out.println("接收到客户端的消息：" + message);

            // 发送响应给客户端
            String response = "Hello, Client!";
            outputStream.write(response.getBytes());

            // 关闭连接
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
