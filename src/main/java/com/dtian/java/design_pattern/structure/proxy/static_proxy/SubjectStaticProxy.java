package com.dtian.java.design_pattern.structure.proxy.static_proxy;

/**
 * 静态代理类
 * 拥有一个Subject引用
 */
public class SubjectStaticProxy implements Subject{
    private Subject subject;

    public SubjectStaticProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        subject.request();
    }
}
