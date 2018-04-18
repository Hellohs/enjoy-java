package com.dtian.java.design_pattern.observer;

public class TestObserver {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        ConcreteObserver a = new ConcreteObserver("a");
        ConcreteObserver b = new ConcreteObserver("b");
        ConcreteObserver c = new ConcreteObserver("c");

        subject.attach(a);
        subject.attach(b);
        subject.attach(c);

        subject.setMsg("hello dt");
    }
}
