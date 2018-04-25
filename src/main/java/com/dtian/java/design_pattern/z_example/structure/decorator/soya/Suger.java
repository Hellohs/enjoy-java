package com.dtian.java.design_pattern.z_example.structure.decorator.soya;

/**
 * 糖，用来修饰纯豆浆
 */
public class Suger extends DrinkDecorator{

    public Suger(Drink drink) {
        super(drink);
    }

    @Override
    protected double money() {
        return drink.money() + 0.5;
    }

    @Override
    protected String description() {
        return drink.description() + " + suger";
    }
}
