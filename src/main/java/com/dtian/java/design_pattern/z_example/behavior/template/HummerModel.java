package com.dtian.java.design_pattern.z_example.behavior.template;

public abstract class HummerModel {

    /**悍马车能被发动*/
    protected abstract void start();
    /**悍马车能被停止*/
    protected abstract void stop();
    /**悍马车鸣笛*/
    protected abstract void alarm();
    /**悍马车引擎轰鸣*/
    protected abstract void engineBoom();
    /**悍马车是否要鸣笛*/
    protected boolean isAlarm(){
        return true;
    }

    /**悍马车跑起来 -- 模板方法 */
    public final void run() {
        start();
        engineBoom();
        if (isAlarm())
            alarm();
        stop();
    }
}
