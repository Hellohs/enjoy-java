package com.dtian.java.design_pattern.behavior.template;

/**
 * 模板方法模式 子类
 */
public class ConcreteClassA extends AbstractClass {
    @Override
    protected void abstractMethod() {
        System.out.println("ConcreteClassA abstractMethod");
    }

    @Override
    protected void hookMethod() {
        System.out.println("ConcreteClassA hookMethod");
    }

    @Override
    protected boolean hookFlag() {
        return true;
    }
}
