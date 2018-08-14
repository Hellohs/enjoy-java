package com.dtian.java.design_pattern.created.factory;

import com.dtian.java.design_pattern.created.factory.simple_factory.ProductSimpleFactory;
import com.dtian.java.design_pattern.created.factory.simple_factory.ProductSimpleFactoryUseReflect;
import com.dtian.java.design_pattern.created.factory.z_product.Product;
import com.dtian.java.design_pattern.created.factory.z_product.ProductA;
import com.dtian.java.design_pattern.created.factory.z_product.ProductB;

/**
 * 简单工厂测试类
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        /**初始化工厂*/
        ProductSimpleFactory simpleFactory = new ProductSimpleFactory();

        /**创建产品*/
        Product a = simpleFactory.createProduct(ProductA.class);
        Product b = simpleFactory.createProduct(ProductB.class);

        /**使用产品*/
        a.doSomething();
        b.doSomething();

        System.out.println("-- -- -- -- -- -- -- --");
        /**下面是对反射简单工厂的测试*/
        ProductSimpleFactoryUseReflect simpleFactoryUseReflect = new ProductSimpleFactoryUseReflect();
        Product a1 = simpleFactoryUseReflect.createProduct(ProductA.class);
        Product b1 = simpleFactoryUseReflect.createProduct(ProductB.class);

        a1.doSomething();
        b1.doSomething();
    }
}
