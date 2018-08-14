package com.dtian.java.design_pattern.created.factory.abstract_factory;

/**
 * 产品族2 产品A
 */
public class ProductA2 extends AbstractProductA{
    public ProductA2() {
        super.name = "ProductA2";
    }

    @Override
    public void specialMethodA() {
        System.out.println("ProductA2 own family 2");
    }

    @Override
    public void doSomething() {
        System.out.println(super.name + " do something");
    }
}
