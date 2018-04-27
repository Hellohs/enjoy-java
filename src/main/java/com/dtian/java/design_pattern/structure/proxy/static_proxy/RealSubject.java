package com.dtian.java.design_pattern.structure.proxy.static_proxy;

/**
 * 接口的真实实现类
 */
public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("real subject do");
    }
}
