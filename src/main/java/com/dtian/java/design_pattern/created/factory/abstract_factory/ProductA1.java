package com.dtian.java.design_pattern.created.factory.abstract_factory;

/**
 * 产品族1 产品A
 */
public class ProductA1 extends AbstractProductA{

    public ProductA1() {
        super.name = "ProductA1";
    }

    @Override
    public void specialMethodA() {
        System.out.println("ProductA1 own family 1");
    }

    @Override
    public void doSomething() {
        System.out.println(super.name + " do something");
    }
}
