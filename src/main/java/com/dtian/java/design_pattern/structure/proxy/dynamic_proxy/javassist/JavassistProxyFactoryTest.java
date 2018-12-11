package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.javassist;

import com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.javassist.factory1.JavassistProxyFactory;

/**
 * JavassistProxyFactory1Test 测试类
 */
public class JavassistProxyFactoryTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        JavassistProxyFactory proxyFactory = new JavassistProxyFactory();
        NameService nameServiceProxy = (NameService)proxyFactory.getProxy(UppercaseNameService.class);
        String uppercaseName = nameServiceProxy.changeName("john walk");
        System.out.println("name:" + uppercaseName);
    }
}
