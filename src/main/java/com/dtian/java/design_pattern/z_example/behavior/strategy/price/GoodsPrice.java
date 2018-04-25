package com.dtian.java.design_pattern.z_example.behavior.strategy.price;

/**
 * 上下文对象,此处上下文对象只是保存了具体的策略，
 * 而具体策略的选择在客户端
 */
public class GoodsPrice {
    //持有一个具体的策略对象
    private MemberStrategy memberStrategy;

    public GoodsPrice(MemberStrategy memberStrategy) {
        this.memberStrategy = memberStrategy;
    }

    public double quotePrice(double goodsPrice){
        return memberStrategy.calcPrice(goodsPrice);
    }
}
