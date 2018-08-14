package com.dtian.java.design_pattern.created.factory.factory_method;

import com.dtian.java.design_pattern.created.factory.z_product.Product;

/**
 * 工厂方法 实现方式一
 * 具体工厂生产具体产品
 * 在此 是通过反射来生产具体的产品
 */
public class ConcreteProductFactory extends AbstractFactoryMethod {
    @Override
    public <T extends Product> T createProduct(Class<T> clz) {
        Product p = null;
        try {
            p = (Product) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            //异常处理
            e.printStackTrace();
        }
        return (T) p;
    }
}
