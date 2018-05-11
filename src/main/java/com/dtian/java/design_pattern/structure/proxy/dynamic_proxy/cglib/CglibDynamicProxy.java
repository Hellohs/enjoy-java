package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理
 * 实现MethodInterceptor 这个方法拦截器
 * 相比于JDK的动态代理，CGLIB动态代理
 *     1：能对class和interface做代理（继承），jdk只能对interface做代理
 *     2：效率更高，因为fastclass机制
 * 不足：对于被final修饰的方法，不能被代理
 */
public class CglibDynamicProxy implements MethodInterceptor {

    /**
     * 动态创建一个代理类
     *
     * @param clazz
     * @return 代理类对象
     */
    public Object newInstance(Class clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        doBefore();
        Object res = proxy.invokeSuper(target, args);
        doAfter();
        return res;
    }

    /**
     * 前置方法
     */
    private void doBefore() {
        System.out.println("do something before method invoke");
    }

    /**
     * 后置方法
     */
    private void doAfter() {
        System.out.println("do something after method invoke");
    }
}
