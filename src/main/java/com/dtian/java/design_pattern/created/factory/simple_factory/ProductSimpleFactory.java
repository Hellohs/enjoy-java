package com.dtian.java.design_pattern.created.factory.simple_factory;

import com.dtian.java.design_pattern.created.factory.z_product.Product;
import com.dtian.java.design_pattern.created.factory.z_product.ProductA;
import com.dtian.java.design_pattern.created.factory.z_product.ProductB;

/**
 * 简单工厂
 *   对于  简单工厂来说，没有抽象的工厂父类，一个工厂创建所有的产品。
 * 存在的问题：
 * 当前能生产 ProductA 和 ProductB
 * 当需要生产ProductC(新建的产品)的时候，那就需要修改代码，违反了开闭原则
 * <p>
 * 所以聪明的程序员小哥哥 想出了 工厂方法模式 {@link }
 */
public class ProductSimpleFactory {

    /**
     * 按照不同的参数 生产不同的产品
     * @param clz 指定Class类
     * @return 返回对应类的实例
     *
     * 存在的问题：如果新增一个产品，就需要修改工厂类，违反了开闭原则
     *
     */
    public Product createProduct(Class clz) {
        switch (clz.getSimpleName()) {
            case "ProductA":
                return new ProductA();
            case "ProductB":
                return new ProductB();
            default:
                return null;
        }
    }
}
