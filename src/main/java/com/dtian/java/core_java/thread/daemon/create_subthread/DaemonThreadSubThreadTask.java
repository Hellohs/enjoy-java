package com.dtian.java.core_java.thread.daemon.create_subthread;

/**
 * 守护线程 子线程任务
 */
public class DaemonThreadSubThreadTask implements Runnable {
    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}
