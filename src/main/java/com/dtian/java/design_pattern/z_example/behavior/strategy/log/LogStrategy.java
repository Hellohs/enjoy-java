package com.dtian.java.design_pattern.z_example.behavior.strategy.log;

/**
 * 记录日志的策略
 */
public interface LogStrategy {
    /**
     * 记录日志
     * @param log
     */
    public void log(String log);
}
