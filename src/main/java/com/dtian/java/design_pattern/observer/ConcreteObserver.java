package com.dtian.java.design_pattern.observer;

/**
 * 具体观察者
 */
public class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Object msg) {
        System.out.println("name:" + name + ", got msg:" + (String) msg);
    }
}
