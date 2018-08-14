package com.dtian.java.design_pattern.created.factory.abstract_factory;

public class Product2_Factory implements AbstractFactory{
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
