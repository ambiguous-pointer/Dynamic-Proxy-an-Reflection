package org.example.chat;


import org.example.print.print.BackgroundColor;
import org.example.print.print.ConsoleString;
import org.example.print.print.FontColor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private List<ClientHandler> clients;
    private List<String> clientIds;

    public ChatServer() {
        clients = new ArrayList<>();
    }

    public void start(int port) {
        try {
            // 创建 ServerSocket 对象，并指定监听的端口号
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("聊天室服务端已启动，监听端口：" + port);

            while (true) {
                // 监听客户端连接
                Socket clientSocket = serverSocket.accept();
                System.out.println("客户端已连接：" + clientSocket);

                // 创建新的客户端处理线程
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message) {
        System.out.println("广播消息：" + message);
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private InputStream inputStream;
        private OutputStream outputStream;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                inputStream = clientSocket.getInputStream();
                outputStream = clientSocket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            try {
                outputStream.write(message.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void sendMessage(String message,String clientId) {
            try {
                outputStream.write(message.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // 读取客户端发送的消息
                    byte[] buffer = new byte[1024];
                    int length = inputStream.read(buffer);
                    if (length == -1) {
                        break;
                    }
                    String message = new String(buffer, 0, length);
                    System.out.println(ConsoleString.str(FontColor.BLACK, BackgroundColor.BLUE, "收到客户端消息：" + message));

                    // 广播消息给其他客户端
                    broadcastMessage(message);
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
                clients.remove(this);
            }
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.start(12345);
    }
}
