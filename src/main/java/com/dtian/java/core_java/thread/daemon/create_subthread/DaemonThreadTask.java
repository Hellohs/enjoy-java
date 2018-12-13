package com.dtian.java.core_java.thread.daemon.create_subthread;

/**
 * 守护线程任务
 */
public class DaemonThreadTask implements Runnable {
    //子线程数组
    Thread[] threads = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread();
            threads[i].start();
            System.out.println("DaemonThread create SubThread " + i + " started");
        }

        for (int i = 0; i < threads.length; i++) {
            System.out.println("SubThread[" + i + "].isDaemon() = " + threads[i].isDaemon() + ".");
        }
        while (true){
            Thread.yield();
        }
    }
}
