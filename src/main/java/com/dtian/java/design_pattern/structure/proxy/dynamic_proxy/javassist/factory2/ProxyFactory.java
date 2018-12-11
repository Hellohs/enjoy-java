package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.javassist.factory2;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;

/**
 * 代理工厂接口
 * @author Dingxc
 */
public interface ProxyFactory {
    <T> T getProxy(Object target, InvocationHandler handler) throws NoSuchMethodException, IllegalAccessException, IOException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException;
}
