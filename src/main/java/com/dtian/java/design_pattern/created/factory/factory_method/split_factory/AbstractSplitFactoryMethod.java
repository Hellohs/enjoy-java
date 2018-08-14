package com.dtian.java.design_pattern.created.factory.factory_method.split_factory;

import com.dtian.java.design_pattern.created.factory.z_product.Product;

/**
 * 工厂方法模式 实现方式二
 * 不同的工厂生产不同的产品
 */
public abstract class AbstractSplitFactoryMethod {
    /**因为是每个特殊的工厂生产特殊的产品，所以在此不需要参数了*/
    public abstract Product createProduct();
}
