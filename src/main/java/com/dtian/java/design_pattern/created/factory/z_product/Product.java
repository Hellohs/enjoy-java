package com.dtian.java.design_pattern.created.factory.z_product;

/**
 * 定义一个抽象的产品父类
 */
public abstract class Product {
    public String name;

    /**
     * share 的方法
     */
    public void shareMethod() {
        System.out.println("product share method");
    }

    /**
     * 子类实现的个性化方法
     */
    public abstract void doSomething();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
