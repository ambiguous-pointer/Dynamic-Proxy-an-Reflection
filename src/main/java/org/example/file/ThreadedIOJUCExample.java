package org.example.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadedIOJUCExample {
    private static final String FILE_PATH = "output.txt";
    private static final Lock lock = new ReentrantLock();
    private static final Condition numberCondition = lock.newCondition();
    private static final Condition letterCondition = lock.newCondition();
    private static volatile boolean isNumberTurn = true;

    public static void main(String[] args) {
        FileWriter writer;
        try {
            writer = new FileWriter(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Failed to create file writer: " + e.getMessage());
            return;
        }

        Thread numberThread = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 1; i <= 26; i++) {
                    while (!isNumberTurn) {
                        numberCondition.await();
                    }
                    writer.write(Integer.toString(i));
                    System.out.println("\u001B[44;30mBefore method invocation:  INFO: \u001B[0m  \u001B[43;30m" + Thread.currentThread().getName() + "\u001B[0m  \u001B[45;30m 执行了write() \u001B[0m");
                    isNumberTurn = false;
                    letterCondition.signal();
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread letterThread = new Thread(() -> {
            try {
                lock.lock();
                for (char c = 'A'; c <= 'Z'; c++) {
                    while (isNumberTurn) {
                        letterCondition.await();
                    }
                    writer.write(Character.toString(c));
                    System.out.println("\u001B[44;30mBefore method invocation:  INFO: \u001B[0m  \u001B[42;30m" + Thread.currentThread().getName() + "\u001B[0m  \u001B[45;30m 执行了write() \u001B[0m");
                    isNumberTurn = true;
                    numberCondition.signal();
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
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