package com.dtian.java.core_java.thread.daemon;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * 守护线程 测试 demo
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        //5秒后执行
        cal.add(Calendar.SECOND, 5);
        //创建一个 守护线程的Timer执行器
        Timer timer = new Timer(true);
        System.out.println("任务提交，时间：" + new Date());
        //开始执行任务
        timer.schedule(new UserTimerTask(), cal.getTime());
        //任务不会被执行，因为程序运行到此处，已经没有用户线程了，
        //所以jvm会退出，所以任务不会被执行
    }
}
