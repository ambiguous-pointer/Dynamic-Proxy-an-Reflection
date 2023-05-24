package org.example.thread.print;

public class AlternatePrintingExample {
    private static final Object lock = new Object();
    private static boolean printNumber = true;

    public static void main(String[] args) {
        Thread numberThread = new Thread(new NumberPrinter());
        Thread letterThread = new Thread(new LetterPrinter());

        numberThread.start();
        letterThread.start();
    }

    private static class NumberPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 26; i++) {
                synchronized (lock) {
                    while (!printNumber) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print(i);
                    System.out.print(i + 1);
                    printNumber = false;
                    lock.notifyAll();
                }
            }
        }
    }

    private static class LetterPrinter implements Runnable {
        @Override
        public void run() {
            for (char c = 'A'; c <= 'Z'; c++) {
                synchronized (lock) {
                    while (printNumber) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print(c);
                    printNumber = true;
                    lock.notifyAll();
                }
            }
        }
    }
}
