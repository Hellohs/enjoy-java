package com.dtian.java.design_pattern.z_example.behavior.strategy.price;

public class MemberStrategyClient {
    public static void main(String[] args) {
        MemberStrategy memberStrategy = new IntermediateMemberStrategy();
        GoodsPrice goodsPrice = new GoodsPrice(memberStrategy);
        System.out.println("商品价格为：" + goodsPrice.quotePrice(2.5));
    }
}
