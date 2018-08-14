package com.dtian.java.design_pattern.created.factory.z_product;

/**
 * 具体的产品 产品B
 */
public class ProductB extends Product{
    public ProductB() {
        super.name = "ProductB";
    }

    @Override
    public void doSomething() {
        System.out.println(super.name + " do something.");
    }
}
