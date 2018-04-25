package com.dtian.java.design_pattern.z_example.behavior.strategy.log;

/**
 * 记录日志的上下文
 * 此处日志保存由上下文来选择，
 * 客户端只需要调用就OK了
 */
public class LogContext {

    /**
     * 记录日志方法，提供给客户端使用
     * @param log 日志
     */
    public void log(String log){
        //先尝试保存日志到数据库
        LogStrategy logStrategy = new DbLog();
        try{
            logStrategy.log(log);
        }catch (Exception exception){
            //容错机制，报错后，记录日志到文件
            //修改策略
            logStrategy = new FileLog();
            logStrategy.log(log);
        }
    }
}
