package com.dtian.java.design_pattern.created.factory.factory_method.split_factory;

import com.dtian.java.design_pattern.created.factory.z_product.Product;
import com.dtian.java.design_pattern.created.factory.z_product.ProductB;

public class ConcreteProductBFactory extends AbstractSplitFactoryMethod {
    @Override
    public Product createProduct() {
        return new ProductB();
    }
}
