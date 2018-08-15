package com.dtian.java.design_pattern.behavior.template;

/**
 * 模板方法模式 子类
 */
public class ConcreteClassB extends AbstractClass{
    @Override
    protected void abstractMethod() {
        System.out.println("ConcreteClassB abstractMethod");
    }

    @Override
    protected void hookMethod() {
        System.out.println("ConcreteClassB hookMethod");
    }

    @Override
    protected boolean hookFlag() {
        return false;
    }
}
