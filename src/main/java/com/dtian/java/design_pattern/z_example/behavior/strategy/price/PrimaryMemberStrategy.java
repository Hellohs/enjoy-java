package com.dtian.java.design_pattern.z_example.behavior.strategy.price;

/**
 * 初级会员策略类
 */
public class PrimaryMemberStrategy implements MemberStrategy{
    @Override
    public double calcPrice(double goodsPrice) {
        System.out.println("对于初级会员没有折扣");
        return goodsPrice;
    }
}
