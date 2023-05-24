package org.example.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient01 {
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public void start(String serverAddress, int serverPort) {
        try {
            // 创建 Socket 对象，连接服务器
            clientSocket = new Socket(serverAddress, serverPort);
            System.out.println("成功连接到服务器：" + clientSocket);

            // 获取输入流和输出流
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();

            // 创建接收消息的线程
            Thread receiver = new Thread(new MessageReceiver());
            receiver.start();

            // 读取用户输入并发送消息
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        try {
            outputStream.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MessageReceiver implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    // 接收服务器发送的消息
                    byte[] buffer = new byte[1024];
                    int length = inputStream.read(buffer);
                    if (length == -1) {
                        break;
                    }
                    String message = new String(buffer, 0, length);
                    System.out.println("收到服务器消息：" + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatClient01 client = new ChatClient01();
        client.start("localhost", 12345);
    }
}

