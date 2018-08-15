package com.dtian.java.design_pattern.created.factory;

import com.dtian.java.design_pattern.created.factory.zcreate_singleton.SingletonFactory;
import com.dtian.java.design_pattern.created.singleton.InnerClassSafetySingleton;

public class SingletonFactoryTest {
    public static void main(String[] args) {
        InnerClassSafetySingleton s1 = InnerClassSafetySingleton.getInstance();
        System.out.println("InnerClassSafetySingleton Instance:" + s1);

        InnerClassSafetySingleton s2 = SingletonFactory.getInstance();
        System.out.println("InnerClassSafetySingleton Instance:" + s2);

        InnerClassSafetySingleton s3 = SingletonFactory.getInstance();
        System.out.println("InnerClassSafetySingleton Instance:" + s3);
    }
}
