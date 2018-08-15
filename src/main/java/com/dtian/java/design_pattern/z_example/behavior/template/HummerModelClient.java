package com.dtian.java.design_pattern.z_example.behavior.template;

public class HummerModelClient {
    public static void main(String[] args) {
        HummerModel h1 = new HummerH1Model();
        h1.run();

        System.out.println("-- -- -- -- -- -- --");

        HummerModel h2 = new HummerH2Model();
        h2.run();
    }
}
