package com.dtian.java.design_pattern.created.factory.z_product;

/**
 * 具体的产品 产品A
 */
public class ProductA extends Product{

    public ProductA() {
        super.name = "ProductA";
    }

    @Override
    public void doSomething() {
        System.out.println(super.name + " do something.");
    }
}
