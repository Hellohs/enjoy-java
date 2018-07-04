package com.dtian.java.core_java.error_analysis.this_escape.demo1;

import java.util.List;

/**
 * ListenerRunnable
 */
public class ListenerRunnable implements Runnable{
    /** EventSource */
    private EventSource<EventListener> source;

    public ListenerRunnable(EventSource<EventListener> source) {
        this.source = source;
    }

    @Override
    public void run() {
        List<EventListener> listeners = null;
        /** 取出 listeners */
        try {
            listeners = this.source.retrieveListeners();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(EventListener listener : listeners){
            listener.onEvent(new Object());  //执行内部类获取外部类的成员变量的方法
        }
    }
}
