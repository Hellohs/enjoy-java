package com.dtian.java.design_pattern.created.singleton;

/**
 * 线程不安全的单例模式（懒汉）
 *     优点：延迟加载，只有在被使用的时候才会去加载资源
 *     缺点：多线程不安全，能够被反序列化，反射产生新的实例
 */
public class OrdinaryNoSafetySingleton {
    private static OrdinaryNoSafetySingleton INSTANCE = null;

    private OrdinaryNoSafetySingleton() {
        System.out.println("Ordinary No Safety Singleton");
    }

    /**
     * 非线程安全
     */
    public static OrdinaryNoSafetySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrdinaryNoSafetySingleton();
        }
        return INSTANCE;
    }

    public static void hello() {
        System.out.println("hello[OrdinaryNoSafetySingleton]");
    }
}
