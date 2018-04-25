package com.dtian.java.design_pattern.z_example.structure.decorator.soya;

public class Milk extends DrinkDecorator{

    public Milk(Drink drink) {
        super(drink);
    }

    @Override
    protected double money() {
        return drink.money() + 1.5;
    }

    @Override
    protected String description() {
        return drink.description() + " + milk";
    }
}
