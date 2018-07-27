package com.dtian.java.concurrent.test_thread;

/**
 * 线程join测试
 */
public class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread sub = new Thread(new SubThread());
        sub.join();
        sub.start();
        /**在子线程sleep 时，如果没有sub.join，主线程会先执行，不会等待子线程*/
        /**在加入sub.join()后，子线程会等待自己执行完成*/
        System.out.println("main thread done");
    }

    static class SubThread implements Runnable {

        @Override
        public void run() {
            /**子线程 sleep*/
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sub thread done");
        }
    }
}
