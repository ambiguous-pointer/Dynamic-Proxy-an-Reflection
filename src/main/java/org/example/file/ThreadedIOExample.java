package org.example.file;

import java.io.FileWriter;
import java.io.IOException;

public class ThreadedIOExample {
    private static final String FILE_PATH = "output.txt";
    private static final Object lock = new Object();

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir").toLowerCase());
        FileWriter writer;
        try {
            writer = new FileWriter(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Failed to create file writer: " + e.getMessage());
            return;
        }

        Thread numberThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i += 2) {
                    synchronized (lock) {
                        writer.write(Integer.toString(i));
                        lock.notify();
                        lock.wait();
                    }
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });

        Thread letterThread = new Thread(() -> {
            try {
                for (char c = 'A'; c <= 'J'; c += 2) {
                    synchronized (lock) {
                        writer.write(Character.toString(c));
                        lock.notify();
                        if (c != 'J') {
                            lock.wait();
                        }
                    }
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });

        numberThread.start();
        letterThread.start();

        try {
            numberThread.join();
            letterThread.join();
            writer.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

