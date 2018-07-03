package com.dtian.java.concurrent.executor.future_task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo2 {
    public static void main(String[] args) {
        /**任务1，模拟获取计算结果*/
        FutureTask<Integer> task1 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("task1 execute");
                /**do something*/
                return 2;
            }
        });

        FutureTask<Integer> task2 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("task2 execute");
                /**do something*/
                return 2;
            }
        });
        /**分配线程执行任务*/
        Thread t1 = new Thread(task1);
        t1.start();
        Thread t2 = new Thread(task2);
        t2.start();
        /**主线程可以去干自己的事情*/
        /**main thread execute*/
        /**获取结果*/
        try {
            Integer res1 = task1.get();
            Integer res2 = task2.get();

            /**获得最后的结果*/
            Integer total = res1 + res2;
            System.out.println("result:" + total);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
