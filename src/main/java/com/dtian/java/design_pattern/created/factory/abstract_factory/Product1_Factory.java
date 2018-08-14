package com.dtian.java.design_pattern.created.factory.abstract_factory;

/**
 * 产品族1 工厂
 */
public class Product1_Factory implements AbstractFactory{

    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
