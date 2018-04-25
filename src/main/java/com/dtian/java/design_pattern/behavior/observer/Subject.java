package com.dtian.java.design_pattern.behavior.observer;

/**
 * 抽象主题
 */
public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObserver();
}
