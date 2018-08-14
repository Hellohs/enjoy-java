package com.dtian.java.design_pattern.created.factory.simple_factory;

import com.dtian.java.design_pattern.created.factory.z_product.Product;

/**
 * 借助Java反射的简单工厂
 */
public class ProductSimpleFactoryUseReflect {

    //简单工厂 ？？？ 是
    /**
     * 如果是基于下面此种实现方式
     * 同样也是 简单工厂（同样没有抽象父类方法，同样只能生产同一类产品），不要认为他是工厂方法模式了
     * 在此处看这个简单工厂
     *     好像是不会因为产品的变更或者产品的增多需要修改工厂类（因为使用了反射），
     * 但是在实际的应用中，会遇到比此处更加复杂的情况，
     * 如 网络的连接，jdbc的获取等，往往不是一个简单的实例获取，
     * 所以，在简单工厂中，还是紧耦合，还是需要修改工厂方法的代码。
     * 这就突出了
     *     工厂方法模式的优点，因为有一个抽象的工厂父类
     * 可以按照不同的子类来实现不同的具体工厂，解耦合
     */
    public Product createProduct(Class clz) {
        Product product = null;
        try {
            product = (Product) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
