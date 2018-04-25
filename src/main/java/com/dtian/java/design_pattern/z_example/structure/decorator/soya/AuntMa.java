package com.dtian.java.design_pattern.z_example.structure.decorator.soya;

public class AuntMa {
    public static void main(String[] args) {
        Drink drink1 = new Milk(new Soya());
        System.out.println(drink1.description() + ", cost:" + drink1.money());

        Drink drink2 = new Milk(new Suger(new Soya()));
        System.out.println(drink2.description() + ", cost:" + drink2.money());
    }
}
