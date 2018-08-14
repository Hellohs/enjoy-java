package com.dtian.java.design_pattern.created.factory.factory_method.split_factory;

import com.dtian.java.design_pattern.created.factory.z_product.Product;
import com.dtian.java.design_pattern.created.factory.z_product.ProductA;

/**
 * 工厂方法模式 实现方式二.ProductA
 * ProductA 的具体工厂
 * <p>
 * 在实际的项目中，如果我们按照 {@link com.dtian.java.design_pattern.created.factory.factory_method.ConcreteProductFactory} 这种方式
 * 把所有的产品生产都放在一个工厂里面，那么，这个工厂的代码势必会非常臃肿，
 * 而且代码的结构会变得很不清晰。
 * 考虑到结构清晰，我们就为每一个产品都定义一个创造者，
 * 例如：
 * {@link ConcreteProductAFactory}
 * {@link ConcreteProductBFactory}
 * 然后让调用者自己去选择与哪个工厂关联。
 * 在这种结构中，每个产品都有自己的创造者，每个创造者独立负责对应产品对象的生产，
 * 非常符合单一职责原则。
 */
public class ConcreteProductAFactory extends AbstractSplitFactoryMethod{
    @Override
    public Product createProduct() {
        return new ProductA();
    }
}
