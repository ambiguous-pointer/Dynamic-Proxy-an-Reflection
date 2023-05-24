package org.example.thread.print;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlternatePrintingJUCExample {
    private static final Lock lock = new ReentrantLock();
    private static final Condition numberCondition = lock.newCondition();
    private static final Condition letterCondition = lock.newCondition();
    private static boolean printNumber = true;

    public static void main(String[] args) {
        Thread numberThread = new Thread(new NumberPrinter(),"NumberPrinter-Thread");
        Thread letterThread = new Thread(new LetterPrinter(),"LetterPrinter-Thread");

        numberThread.start();
        letterThread.start();
    }

    private static class NumberPrinter implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 1; i <= 26; i++) {
                    while (!printNumber) {
                        try {
                            numberCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print(i + "\t");
                    printNumber = false;
                    letterCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private static class LetterPrinter implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                for (char c = 'A'; c <= 'Z'; c++) {
                    while (printNumber) {
                        try {
                            letterCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(c);
                    printNumber = true;
                    numberCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
