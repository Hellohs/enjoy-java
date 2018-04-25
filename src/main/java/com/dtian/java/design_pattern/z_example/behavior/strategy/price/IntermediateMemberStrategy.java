package com.dtian.java.design_pattern.z_example.behavior.strategy.price;

/**
 * 中级会员策略类
 */
public class IntermediateMemberStrategy implements MemberStrategy{
    @Override
    public double calcPrice(double goodsPrice) {
        System.out.println("对于中级会员的折扣为10%");
        return goodsPrice * 0.9;
    }
}
