package com.dtian.java.design_pattern.z_example.structure.decorator.soya;

/**
 * 装饰者
 */
public class DrinkDecorator extends Drink{
    protected Drink drink;

    public DrinkDecorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    protected double money() {
        return drink.money();
    }

    @Override
    protected String description() {
        return drink.description();
    }
}
