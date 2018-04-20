package com.dtian.java.design_pattern.created.singleton;

/**
 * 静态内部类 线程安全 懒汉模式单例
 *     优点：延迟加载，线程安全，性能最优
 */
public class InnerClassSafetySingleton {

    private InnerClassSafetySingleton() {
        System.out.println("Inner Class Safety Singleton");
    }

    /**
     * 直到方法被调用的时候，静态内部类才会被加载，
     * 做到了延迟加载，
     * 同时又由JVM保证了在多线程环境下，
     * 并发访问的正确性
     *
     * @return InnerClassSafetySingleton
     */
    public static InnerClassSafetySingleton getInstance() {
        return InnerClassSafetySingletonHolder.INSTANCE;
    }

    public static void hello() {
        System.out.println("hello[InnerClassSafetySingleton]");
    }

    static class InnerClassSafetySingletonHolder {
        private static volatile InnerClassSafetySingleton INSTANCE = new InnerClassSafetySingleton();
    }
}
