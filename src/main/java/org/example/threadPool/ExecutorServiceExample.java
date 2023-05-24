package org.example.threadPool;

import java.util.concurrent.*;

public class ExecutorServiceExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Integer> task1 = () -> {
            System.out.println("Task 1 started");
            Thread.sleep(2000);
            System.out.println("Task 1 completed");
            return 1;
        };

        Callable<Integer> task2 = () -> {
            System.out.println("Task 2 started");
            Thread.sleep(1000);
            System.out.println("Task 2 completed");
            return 2;
        };

        Future<Integer> future1 = executorService.submit(task1);
        Future<Integer> future2 = executorService.submit(task2);

        Integer result1 = future1.get();
        Integer result2 = future2.get();

        System.out.println("Result 1: " + result1);
        System.out.println("Result 2: " + result2);

        executorService.shutdown();
    }
}