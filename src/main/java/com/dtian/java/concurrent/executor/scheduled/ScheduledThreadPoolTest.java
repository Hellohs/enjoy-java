package com.dtian.java.concurrent.executor.scheduled;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 用来测试 ScheduledThreadPoolExecutor
 * 4个方法：
 *   延迟 delay 时间（TimeUnit 时间单元），后执行Runnable任务
 *   schedule(Runnable command, long delay, TimeUnit unit)
 *
 *   延迟 delay 时间（TimeUnit 时间单元），后执行Callable任务
 *   schedule(Callable<V> callable, long delay, TimeUnit unit)
 *
 *   延迟initialDelay 时间（TimeUnit 时间单元），周期性（period时间）的执行Runnable任务
 *
 *   也就是说任务第一次运行时间是initialDelay，第二次运行时间是initialDelay+period，
 *   在initialDelay之后开始运行任务，
 *   当任务完成之后，将当前时间与initialDelay+period时间进行比较，如果小于initialDelay+period时间，那么等待，
 *   如果大于initialDelay+period时间，那么就直接执行第二次任务
 *   scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
 *
 *   在给定的初始化延时initialDelay之后，开始执行任务，任务执行完成之后，等待delay时间，再一次执行任务。
 *   因为它是等待任务完成之后，再进行延迟，就不会受任务完成时间长短地影响。
 *   scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
 */
public class ScheduledThreadPoolTest {

    static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        System.out.println("command submit:" + SDF.format(System.currentTimeMillis()));

        //test1
        /** 单次任务，延迟2秒后执行任务
         * 这里就对runnable 进行了测试，callable 同理 */
        /*scheduledThreadPool.schedule(new ScheduledThreadPoolTestRunnable(), 2L, TimeUnit.SECONDS);
        scheduledThreadPool.shutdown();*/

        //test2-1
        /** 0延迟执行，每次周期性延迟2秒，再执行任务，
         * 因为任务需要执行5秒，
         * 所以可以发现，
         * 一次任务执行完之后，立马开始执行下一次任务 */
        //scheduledThreadPool.scheduleAtFixedRate(new ScheduledThreadPoolTestRunnable(), 0L, 2L, TimeUnit.SECONDS);

        //test2-2
        /** 0延迟执行，每次周期性延迟7秒，再执行任务，
         * 因为任务需要执行5秒，
         * 所以可以发现，
         * 一次任务执行完之后，需要等待2秒 才开始执行下一次任务(也可以理解每次间隔7秒执行) */
        //scheduledThreadPool.scheduleAtFixedRate(new ScheduledThreadPoolTestRunnable(), 0L, 10L, TimeUnit.SECONDS);

        //test3-1
        /**
         * 0延迟执行，每次周期性延迟5秒，执行下一个任务
         * 因为任务需要执行5秒，
         * 所以：一次任务执行完之后，等待5秒，开始执行下一个任务
         */
        //scheduledThreadPool.scheduleWithFixedDelay(new ScheduledThreadPoolTestRunnable(), 0L, 5L, TimeUnit.SECONDS);

        //test3-2
        /**
         * 0延迟执行，每次周期性延迟1秒，执行下一个任务
         * 因为任务需要执行5秒，
         * 所以：一次任务执行完之后，等待1秒，开始执行下一个任务
         */
        scheduledThreadPool.scheduleWithFixedDelay(new ScheduledThreadPoolTestRunnable(), 0L, 1L, TimeUnit.SECONDS);
    }

    static class ScheduledThreadPoolTestRunnable implements Runnable {

        int times;

        @Override
        public void run() {
            times++;
            System.out.println("runnable start:" + SDF.format(System.currentTimeMillis()) + ", times:" + times);
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
