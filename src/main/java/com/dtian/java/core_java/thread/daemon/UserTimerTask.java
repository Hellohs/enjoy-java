package com.dtian.java.core_java.thread.daemon;

import java.util.Date;
import java.util.TimerTask;

/**
 * 用户任务
 */
public class UserTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("任务执行，时间：" + new Date());
    }
}
