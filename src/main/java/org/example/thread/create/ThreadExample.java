package org.example.thread.create;

import java.util.concurrent.*;

/**
 * Hello world!
 */
public class ThreadExample {
    private int num = 0;

    public static void main(String[] args) {
        ThreadExample app = new ThreadExample();
        app.fun();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Thread(() -> {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                }
            }, "Executors.newFixedThreadPool"));
        }
    }

    private void fun1() {
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("\n \u001B[42;30mINFO: ==============> running ..........\u001B[0m\t======一号线程:" + i + "=======");
            }
        };
        runnable.run();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("\n \u001B[43;30mINFO: ==============> running ..........\u001B[0m\t======二号线程:" + i + "=======");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("\n \u001B[44;30mINFO: ==============> running ..........\u001B[0m\t======三号线程:" + i + "=======");
            }
        }).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("\n \u001B[45;30mINFO: ==============> running ..........\u001B[0m\t======main 线程:" + i + "=======");
        }
    }

    private void fun() {
        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName());
            }
        }, "Thread-01").start();
        new Thread((Runnable) () -> {
            while (true) {
                System.out.println(Thread.currentThread().getName());
            }
        }, "Runnable-01").start();

        new Thread((Runnable) () -> {
            while (true) {
                System.out.println(Thread.currentThread().getName());
            }
        }, "Runnable-02").start();
        FutureTask<String> stringFutureTask = new FutureTask<>(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + i);
            }
            return "OK";
        });
        new Thread(stringFutureTask, "Callable-01").start();
        try {
            System.out.println(stringFutureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
