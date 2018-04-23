package com.dtian.java.design_pattern.structure.decorator;

/**
 * 具体装饰者B，把附加的责任，加到被装饰者上
 */
public class ConcreteDecoratorB extends Decorator{

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    void operation() {
        System.out.println("concrete decorator b do: operation");
        /**do some thing*/
        super.operation();
        /**do some thing*/
    }
}
