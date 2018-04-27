package com.dtian.java.design_pattern.structure.proxy.static_proxy;

public class StaticProxyClient {
    public static void main(String[] args) {
        Subject proxy = new SubjectStaticProxy(new RealSubject());
        proxy.request();
    }
}
