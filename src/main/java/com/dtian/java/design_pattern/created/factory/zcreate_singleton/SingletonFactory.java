package com.dtian.java.design_pattern.created.factory.zcreate_singleton;

import com.dtian.java.design_pattern.created.singleton.InnerClassSafetySingleton;

import java.lang.reflect.Constructor;

/**
 * 在日常的开发中，我们经常会使用到单例模式，
 * 单例模式的构造函数是私有的，所以我们不能通过new的方式去创建。
 * 一般会通过getInstance 方法返回单例的唯一实例，
 * 并且这个唯一实例在整个应用中都是可用的。
 * <p>
 * 但是在此处，我们可以通过java反射的方式，再来创建一个单例类的实例，
 * 可以算是说，单例模式不单例的 举例了。
 */
public class SingletonFactory {
    private static InnerClassSafetySingleton singleton;

    static {
        Class clz = null;
        try {
            /** 通过反射获取class类信息*/
            clz = Class.forName(InnerClassSafetySingleton.class.getName());
            /**获取构造函数*/
            Constructor constructor = clz.getDeclaredConstructor();
            /**设置构造函数可用*/
            constructor.setAccessible(true);
            /**创建实例*/
            singleton = (InnerClassSafetySingleton) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InnerClassSafetySingleton getInstance() {
        return singleton;
    }
}
