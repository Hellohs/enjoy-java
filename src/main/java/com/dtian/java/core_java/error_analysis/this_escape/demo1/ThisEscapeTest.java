package com.dtian.java.core_java.error_analysis.this_escape.demo1;

public class ThisEscapeTest {
    public static void main(String[] args) {
        /** source */
        EventSource<EventListener> source = new EventSource<EventListener>();
        /** ListenerRunnable */
        ListenerRunnable lr = new ListenerRunnable(source);
        Thread t = new Thread(lr);
        /**当有事件注册的时候，就会调用onEvent方法，就会出现 name为 null的现象*/
        t.start();
        /** 注册事件发生 */
        ThisEscape thisEscape = new ThisEscape(source);
    }
}
