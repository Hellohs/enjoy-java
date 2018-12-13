package com.dtian.java.core_java.thread.daemon;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * 用户线程测试类
 */
public class UserThreadDemo {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        //5秒后执行
        cal.add(Calendar.SECOND, 5);
        //创建一个 非守护线程的Timer执行器
        Timer timer = new Timer();
        System.out.println("任务提交，时间：" + new Date());
        //开始执行任务
        timer.schedule(new UserTimerTask(), cal.getTime());
        //任务执行完成后，jvm不会退出，因为用户线程被 start() 了
    }
}
