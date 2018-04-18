package com.dtian.java.design_pattern.observer;

/**
 * 抽象主题
 */
public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObserver();
}
