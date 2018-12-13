package com.dtian.java.core_java.thread.daemon.create_subthread;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程 创建 子线程 测试类
 */
public class DaemonThreadCreateSubThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new DaemonThreadTask());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon() = " + d.isDaemon() + ".");
        TimeUnit.SECONDS.sleep(2);
        //sleep 注释与不注释的区别是
        //不注释：主线程sleep状态，主线程（守护线程）创建的子线程（也是守护线程）还会运行
        //注释：主线程执行完成，创建的子线程任务不会运行，因为jvm退出了
    }
}
