package com.dtian.java.concurrent.executor.future_task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * FutureTask测试代码 - demo1
 * FutureTask 是对Callable 的合理使用，
 * 可以获得多线程计算的结果，是对Runnable的一种很好的补充
 */
public class FutureTaskDemo1 {
    /**
     * 使用线程池，得到返回结果
     */
    public static void main(String[] args) {
        /** 定义一个线程池 */
        ExecutorService executorService = Executors.newCachedThreadPool();
        /** 定义一个获取 Future 的列表 */
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        /** 循环提交任务到线程池执行*/
        for (int i = 0; i < 5; i++) {
            futureList.add(executorService.submit(new SumTask(i + 1)));
        }

        /** 获取执行结果 */
        for (Future<String> f : futureList) {
            String rs = null;
            try {
                /** 线程会阻塞，直到结果返回 */
                rs = f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("result:" + rs);
        }
    }
}

/**
 * 计算任务
 */
class SumTask implements Callable<String> {
    int num;

    SumTask(int num) {
        this.num = num;
    }

    @Override
    public String call() throws Exception {
        System.out.println("task:" + num + " execute");
        return "Task " + num + ", return result:" + num;
    }
}
