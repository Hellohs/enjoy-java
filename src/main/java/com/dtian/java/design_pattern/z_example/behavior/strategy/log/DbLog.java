package com.dtian.java.design_pattern.z_example.behavior.strategy.log;

public class DbLog implements LogStrategy{
    @Override
    public void log(String log) {
        if (log != null && log.trim().length() > 10){
            //制造一个错误
            int a = 5/0;
        }
        System.out.println("日志保存到数据库，log:" + log);
    }
}
