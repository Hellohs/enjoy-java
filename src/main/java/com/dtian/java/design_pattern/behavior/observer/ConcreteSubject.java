package com.dtian.java.design_pattern.behavior.observer;

import java.util.Vector;

public class ConcreteSubject implements Subject {

    private Vector<Observer> observers;
    private String msg;

    public ConcreteSubject() {
        observers = new Vector<Observer>();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        if (!observers.isEmpty()) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(this.msg);
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyObserver();
    }
}
