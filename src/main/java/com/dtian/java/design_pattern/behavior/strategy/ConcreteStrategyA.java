package com.dtian.java.design_pattern.behavior.strategy;

/**
 * 实现具体的算法类,A
 */
public class ConcreteStrategyA implements Strategy{
    @Override
    public void algorithmInterface() {
        //具体算法的实现
        System.out.println("i am concrete strategyA algorithm");
    }
}
