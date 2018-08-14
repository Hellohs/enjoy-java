package com.dtian.java.design_pattern.created.factory.factory_method;

import com.dtian.java.design_pattern.created.factory.z_product.Product;

/**
 * 抽象工厂父类
 * 具体的工厂 由子类实现
 * 针对 简单工厂模式的不足，
 * 将具体需要生产什么产品，交给特别的工厂
 * {@link ConcreteProductFactory}
 */
public abstract class AbstractFactoryMethod {
    public abstract <T extends Product> T createProduct(Class<T> clz);
}
