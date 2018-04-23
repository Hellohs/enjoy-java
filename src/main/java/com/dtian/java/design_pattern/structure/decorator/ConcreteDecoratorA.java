package com.dtian.java.design_pattern.structure.decorator;

/**
 * 具体装饰者A，把附加的责任，加到被装饰者上
 */
public class ConcreteDecoratorA extends Decorator{

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    void operation() {
        System.out.println("concrete decorator a do: operation");
        /**do some thing*/
        super.operation();
        /**do some thing*/
    }
}
