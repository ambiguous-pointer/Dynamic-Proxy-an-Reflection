package org.example.sockert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // 创建 Socket 对象，并指定服务器的 IP 地址和端口号
            Socket socket = new Socket("localhost", 12345);

            // 获取输入流和输出流
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // 发送消息给服务器
            String message = "Hello, Server!";
            outputStream.write(message.getBytes());

            // 接收服务器的响应
            byte[] buffer = new byte[1024];
            int length = inputStream.read(buffer);
            String response = new String(buffer, 0, length);
            System.out.println("接收到服务器的响应：" + response);

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
