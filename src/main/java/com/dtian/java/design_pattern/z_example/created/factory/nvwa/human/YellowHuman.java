package com.dtian.java.design_pattern.z_example.created.factory.nvwa.human;

public abstract class YellowHuman implements Human{
    @Override
    public void laugh() {
        System.out.println("黄种人会笑...");
    }

    @Override
    public void cry() {
        System.out.println("黄种人会哭...");
    }

    @Override
    public void talk() {
        System.out.println("黄种人会说话，我说的是汉语...");
    }
}
