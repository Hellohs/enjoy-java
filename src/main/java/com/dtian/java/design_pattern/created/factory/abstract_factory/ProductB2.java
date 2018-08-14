package com.dtian.java.design_pattern.created.factory.abstract_factory;

/**
 * 产品族2 产品B
 */
public class ProductB2 extends AbstractProductB{
    public ProductB2() {
        super.name = "ProductB2";
    }

    @Override
    public void specialMethodB() {
        System.out.println("ProductB2 own family 2");
    }

    @Override
    public void doSomething() {
        System.out.println(super.name + " do something");
    }
}
