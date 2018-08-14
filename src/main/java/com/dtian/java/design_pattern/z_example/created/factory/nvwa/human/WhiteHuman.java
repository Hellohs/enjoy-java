package com.dtian.java.design_pattern.z_example.created.factory.nvwa.human;

public abstract class WhiteHuman implements Human {

    @Override
    public void laugh() {
        System.out.println("白种人会笑...");
    }

    @Override
    public void cry() {
        System.out.println("白种人会哭...");
    }

    @Override
    public void talk() {
        System.out.println("白种人会说话，大部分说的是英语...");
    }

}
