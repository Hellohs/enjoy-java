package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.jdk;

public class JdkDynamicProxyClient {
    public static void main(String[] args) {
        UserService dynamicProxy = (UserService) new JdkDynamicProxy().getInstance(new UserServiceImpl());
        String user = dynamicProxy.queryUser(1);
        System.out.println("result:" + user);
    }
}
