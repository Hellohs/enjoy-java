package com.dtian.java.concurrent.executor.thread_pool;

import java.util.concurrent.*;

/**
 * 线程池示例测试类
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**创建一个不定长的线程池*/
        ExecutorService threadPool = Executors.newCachedThreadPool();
        /**提交任务*/
        Future<String> res = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "result";
            }
        });
        /**会阻塞*/
        System.out.println("future get:" + res.get());
        /**用完线程池，如果需要关闭，则 关闭*/
        threadPool.shutdown();
    }
}
