package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.javassist;


public class TestServiceImpl implements TestService{
    @Override
    public void test(String arg) {
        System.out.println("TestService execute, arg:" + arg);
    }
}
