package com.dtian.java.design_pattern.z_example.structure.decorator.soya;

/**饮料基类*/
public abstract class Drink {

    /**获取价格*/
    protected abstract double money();
    /**返回商品信息*/
    protected abstract String description();
}
