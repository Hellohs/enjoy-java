package com.dtian.java.design_pattern.structure.decorator;

public class TestDecorator {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Component decorator1 = new ConcreteDecoratorA(component);
        Component decorator2 = new ConcreteDecoratorB(decorator1);
        decorator2.operation();
    }
}
