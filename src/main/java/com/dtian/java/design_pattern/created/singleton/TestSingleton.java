package com.dtian.java.design_pattern.created.singleton;

public class TestSingleton {
    public static void main(String[] args) {
        OrdinaryNoSafetySingleton.hello();
        System.out.println("-----------------------------------------");
        /**饥汉模式，构造函数被调用了*/
        OrdinarySafetySingleton.hello();
        System.out.println("-----------------------------------------");
        OrdinarySynchronizedSafetySingleton.hello();
        System.out.println("-----------------------------------------");
        InnerClassSafetySingleton.hello();
        System.out.println("=========================================");

        OrdinaryNoSafetySingleton.hello();
        OrdinaryNoSafetySingleton.getInstance();
        System.out.println("-----------------------------------------");
        OrdinarySafetySingleton.hello();
        OrdinarySafetySingleton.getInstance();
        System.out.println("-----------------------------------------");
        OrdinarySynchronizedSafetySingleton.hello();
        OrdinarySynchronizedSafetySingleton.getInstance();
        System.out.println("-----------------------------------------");
        InnerClassSafetySingleton.hello();
        InnerClassSafetySingleton.getInstance();
        System.out.println("=========================================");
    }
}
