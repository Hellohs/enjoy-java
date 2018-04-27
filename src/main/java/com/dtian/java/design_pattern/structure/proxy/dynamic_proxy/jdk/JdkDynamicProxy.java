package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 * 必须实现 InvocationHandler 接口
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object target;

    /**
     * 获取动态代理类
     * @param target 真实实现接口
     * @return 返回动态生成的代理类
     */
    public Object getInstance(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * @param proxy 生成的动态代理类
     * @param method 调用接口的方法
     * @param args 参数
     * @return 返回调用的结果
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        return method.invoke(target, args);
    }

    private void doBefore() {
        System.out.println("Jdk Dynamic Proxy,do before");
    }
}
