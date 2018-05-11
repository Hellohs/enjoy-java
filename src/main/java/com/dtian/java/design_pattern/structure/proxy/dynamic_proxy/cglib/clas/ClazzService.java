package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.cglib.clas;

/**
 * 被代理的类，target
 */
public class ClazzService {

    public String queryClassByName(String name) {
        System.out.println("query class by name[class]");
        return "name: Test";
    }
}
