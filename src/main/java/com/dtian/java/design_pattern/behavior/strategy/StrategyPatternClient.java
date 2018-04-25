package com.dtian.java.design_pattern.behavior.strategy;

public class StrategyPatternClient {
    public static void main(String[] args) {
        Strategy a = new ConcreteStrategyA();
        Context context = new Context(a);
        context.contextInterface();
        /**修改策略,换成B策略*/
        context = new Context(new ConcreteStrategyB());
        context.contextInterface();
    }
}
