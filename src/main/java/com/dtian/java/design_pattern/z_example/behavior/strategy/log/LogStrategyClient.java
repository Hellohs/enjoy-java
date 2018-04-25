package com.dtian.java.design_pattern.z_example.behavior.strategy.log;

public class LogStrategyClient {
    public static void main(String[] args) {
        LogContext logContext = new LogContext();
        logContext.log("abcd");
        logContext.log("abcdefghijk");
    }
}
