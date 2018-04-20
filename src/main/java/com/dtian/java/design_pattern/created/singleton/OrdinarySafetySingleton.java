package com.dtian.java.design_pattern.created.singleton;

/**
 * 饥汉模式（单例）
 *     优点：线程安全，绝对单例
 *     缺点：非延迟加载，浪费内存，在没有被使用的时候就已经被加载
 */
public class OrdinarySafetySingleton {
    private static final OrdinarySafetySingleton INSTANCE = new OrdinarySafetySingleton();

    /**
     * 构造函数 不管在何时，都会随着INSTANCE的初始化而被调用
     */
    private OrdinarySafetySingleton() {
        System.out.println("Ordinary Safety Singleton");
    }

    public static OrdinarySafetySingleton getInstance() {
        return INSTANCE;
    }

    public static void hello() {
        System.out.println("hello[OrdinarySafetySingleton]");
    }
}
