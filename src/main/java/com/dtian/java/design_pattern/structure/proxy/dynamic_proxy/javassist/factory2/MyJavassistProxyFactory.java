package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.javassist.factory2;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;

/**
 * 具体代理工厂类，用来创建代理类
 */
public class MyJavassistProxyFactory implements ProxyFactory {

    @Override
    public <T> T getProxy(Object target, InvocationHandler handler) throws NoSuchMethodException, IllegalAccessException, IOException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException {
        return (T) ProxyGenerator.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass(), handler);
    }
}
