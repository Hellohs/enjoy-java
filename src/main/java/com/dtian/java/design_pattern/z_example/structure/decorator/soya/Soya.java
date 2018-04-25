package com.dtian.java.design_pattern.z_example.structure.decorator.soya;

/**
 * 被装饰者
 */
public class Soya extends Drink{
    @Override
    protected double money() {
        return 1;
    }

    @Override
    protected String description() {
        return "soya";
    }
}
