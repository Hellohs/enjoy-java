package com.dtian.java.design_pattern.created.factory;

import com.dtian.java.design_pattern.created.factory.factory_method.AbstractFactoryMethod;
import com.dtian.java.design_pattern.created.factory.factory_method.ConcreteProductFactory;
import com.dtian.java.design_pattern.created.factory.factory_method.split_factory.AbstractSplitFactoryMethod;
import com.dtian.java.design_pattern.created.factory.factory_method.split_factory.ConcreteProductAFactory;
import com.dtian.java.design_pattern.created.factory.factory_method.split_factory.ConcreteProductBFactory;
import com.dtian.java.design_pattern.created.factory.z_product.Product;
import com.dtian.java.design_pattern.created.factory.z_product.ProductA;
import com.dtian.java.design_pattern.created.factory.z_product.ProductB;

/**
 * 工厂方法模式 测试类
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        AbstractFactoryMethod factory = new ConcreteProductFactory();
        Product a = factory.createProduct(ProductA.class);
        Product b = factory.createProduct(ProductB.class);
        a.doSomething();
        b.doSomething();

        System.out.println("--- --- --- ---- --- ---");
        AbstractSplitFactoryMethod productAFactory = new ConcreteProductAFactory();
        AbstractSplitFactoryMethod productBFactory = new ConcreteProductBFactory();

        Product a1 = productAFactory.createProduct();
        Product b1 = productBFactory.createProduct();
        a1.doSomething();
        b1.doSomething();
    }
}
