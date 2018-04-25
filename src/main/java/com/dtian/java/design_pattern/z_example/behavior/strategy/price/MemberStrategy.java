package com.dtian.java.design_pattern.z_example.behavior.strategy.price;

/**
 * 价格策略，定义计算价格的接口
 */
public interface MemberStrategy {
    /**
     * 按照不同会员等级，计算不同商品价格
     * @param goodsPrice 原商品的价格
     * @return 返回计算后，商品的价格
     */
    public double calcPrice(double goodsPrice);
}
