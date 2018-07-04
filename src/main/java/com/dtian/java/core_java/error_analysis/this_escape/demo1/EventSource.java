package com.dtian.java.core_java.error_analysis.this_escape.demo1;


import java.util.ArrayList;
import java.util.List;

/**
 * 提供
 * 注册事件
 * 取回事件
 *
 * @param <T>
 */
public class EventSource<T> {
    /** eventListeners */
    private final List<T> eventListeners;

    public EventSource() {
        this.eventListeners = new ArrayList<T>();
    }

    /** 注册Listener */
    public synchronized void registerListener(T eventListener) {
        this.eventListeners.add(eventListener);
        this.notifyAll();
    }

    /** 取出 Listeners 遍历 */
    public synchronized List<T> retrieveListeners() throws InterruptedException {
        List<T> dest = null;
        if (this.eventListeners.size() == 0) {
            this.wait();
        }
        dest = new ArrayList<>(eventListeners.size());
        dest.addAll(eventListeners);
        return dest;
    }
}
