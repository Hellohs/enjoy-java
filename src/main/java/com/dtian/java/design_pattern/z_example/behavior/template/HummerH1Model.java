package com.dtian.java.design_pattern.z_example.behavior.template;

public class HummerH1Model extends HummerModel{

    private boolean alarmFlag = false;
    @Override
    protected void start() {
        System.out.println("悍马H1发动...");
    }

    @Override
    protected void stop() {
        System.out.println("悍马H1停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("悍马H1鸣笛，滴滴滴");
    }

    @Override
    protected void engineBoom() {
        System.out.println("悍马H1引擎 boom...");
    }

    @Override
    protected boolean isAlarm() {
        return this.alarmFlag;
    }

    /**提供给客户 设置是否 需要鸣笛*/
    public void setAlarmFlag(boolean alarmFlag) {
        this.alarmFlag = alarmFlag;
    }
}
