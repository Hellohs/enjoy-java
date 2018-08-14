package com.dtian.java.design_pattern.created.factory.abstract_factory;

/**
 * 产品族1 产品B
 */
public class ProductB1 extends AbstractProductB {
    public ProductB1() {
        super.name = "ProductB1";
    }

    @Override
    public void specialMethodB() {
        System.out.println("ProductB1 own family 1");
    }

    @Override
    public void doSomething() {
        System.out.println(super.name + " do something");
    }
}
