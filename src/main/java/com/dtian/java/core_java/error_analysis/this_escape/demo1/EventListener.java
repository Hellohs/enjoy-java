package com.dtian.java.core_java.error_analysis.this_escape.demo1;

/**定义一个时间监听的接口，
 * 里面没有任何属性，
 * 只有一个方法，用来接收obj的事件通知
 */
public interface EventListener {
    public void onEvent(Object obj);
}
