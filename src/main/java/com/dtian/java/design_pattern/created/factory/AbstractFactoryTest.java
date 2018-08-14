package com.dtian.java.design_pattern.created.factory;

import com.dtian.java.design_pattern.created.factory.abstract_factory.*;
import com.dtian.java.design_pattern.created.factory.z_product.Product;

/**
 * 抽象工厂测试类
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        /**产品族1 的创建*/
        AbstractFactory factory1 = new Product1_Factory();

        AbstractProductA a1 = factory1.createProductA();
        AbstractProductB b1 = factory1.createProductB();
        a1.doSomething();
        a1.specialMethodA();

        b1.doSomething();
        b1.specialMethodB();

        System.out.println("-- -- -- -- -- -- --");
        /**产品族2 的创建*/
        AbstractFactory factory2 = new Product2_Factory();

        AbstractProductA a2 = factory2.createProductA();
        AbstractProductB b2 = factory2.createProductB();

        a2.doSomething();
        a2.specialMethodA();

        b2.doSomething();
        b2.specialMethodB();
    }
}
