package org.example.thread.interrupt;

public class InterruptExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable(), "Thread-01");
        Thread thread1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + "线程正在执行...");
                try {
                    // 模拟耗时操作
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 恢复中断状态，并退出循环
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + "线程在睡眠期间收到中断请求，即将退出...");
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName() + "线程已退出。");
        }, "Thread-02");
        Thread thread2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + "线程正在执行...");

                // 检查中断状态
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "线程收到中断请求，即将退出...");
                    break;
                }

                try {
                    // 模拟耗时操作
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 检查中断状态
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(Thread.currentThread().getName() + "线程在睡眠期间收到中断请求，即将退出...");
                        break;
                    }
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(Thread.currentThread().getName() + "出现中断异常");
                }
            }
            System.out.println(Thread.currentThread().getName() + "线程已退出。");
        }, "Thread-03");

        thread.start();
        thread1.start();
        thread2.start();

        // 让主线程休眠一段时间
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 请求停止线程
        thread.interrupt();
        thread1.interrupt();
        thread2.interrupt();
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + "线程正在执行...");

                // 检查中断状态
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程收到中断请求，即将退出...");
                    break;
                }

                try {
                    // 模拟耗时操作
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 检查中断状态
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(Thread.currentThread().getName() + "线程在睡眠期间收到中断请求，即将退出...");
                        break;
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + "线程已退出。");
        }
    }
}