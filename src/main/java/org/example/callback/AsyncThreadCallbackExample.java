package org.example.callback;

import java.util.UUID;
import java.util.function.BiFunction;

public class AsyncThreadCallbackExample {

    public static void main(String[] args) {
        Task task = new Task();
        Callback callback = new Callback<String, String>() {
            @Override
            public String onComplete(String result) {
                System.out.println("Callback: Task completed with result - " + result);
                return "这是我在回调函数内的数据";
            }

            @Override
            public String onError(Exception e) {
                System.out.println("Callback: Task encountered an error - " + e.getMessage());
                return "error";
            }
        };
        System.out.println(1);
        task.doAsyncOperation(callback);
        BiFunction<String, String, Boolean> biFunction = (s, s2) -> {
            if (s != null && s2 != null && s.equals(s2)) {
                return true;
            } else {
                return false;
            }
        };
        System.out.println(2);
    }

    public interface Callback<T, R> {
        R onComplete(T result);

        R onError(Exception e);
    }

    public static class AsyncTask<T, R, V> {
        V apply(BiFunction function) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    Object apply = function.apply(UUID.randomUUID(), UUID.randomUUID());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "AsyncTask" + UUID.randomUUID().toString()).start();
            return (V) new Object();
        }
    }

    public static class Task {
        public void doAsyncOperation(Callback callback) {
            new Thread(() -> {
                try {
                    // 模拟异步操作
                    Thread.sleep(2000);
                    String result = "Async operation result";
                    String o = (String) callback.onComplete(result);
                    System.out.println(o);
                } catch (Exception e) {
                    callback.onError(e);
                }
            }).start();
        }

    }
}