package com.dtian.test.design_pattern.observer;

/**
 * 抽象主题
 */
public interface Subject {
    public void attach(Observer o);
    public void detach(Observer o);
    public void notifyObserver();
}
