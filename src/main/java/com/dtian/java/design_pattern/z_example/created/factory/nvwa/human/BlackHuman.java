package com.dtian.java.design_pattern.z_example.created.factory.nvwa.human;

public abstract class BlackHuman implements Human {

    @Override
    public void laugh() {
        System.out.println("黑种人会笑...");
    }

    @Override
    public void cry() {
        System.out.println("黑种人会哭...");
    }

    @Override
    public void talk() {
        System.out.println("黑种人说话，一般听不懂...");
    }
}
