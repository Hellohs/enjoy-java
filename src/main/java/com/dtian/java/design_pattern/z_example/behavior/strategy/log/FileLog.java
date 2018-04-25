package com.dtian.java.design_pattern.z_example.behavior.strategy.log;

public class FileLog implements LogStrategy{
    @Override
    public void log(String log) {
        System.out.println("日志保存到文件，log:" + log);
    }
}
