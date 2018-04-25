package com.dtian.java.design_pattern.z_example.behavior.strategy.price;

/**
 * 高级会员策略类
 */
public class AdvancedMemberStrategy implements MemberStrategy{
    @Override
    public double calcPrice(double goodsPrice) {
        System.out.println("对于高级会员的折扣为20%");
        return goodsPrice * 0.8;
    }
}
