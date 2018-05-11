package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.cglib;

import com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.cglib.clas.ClazzService;
import com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.cglib.interfae.InterfaceService;
import com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.cglib.interfae.impl.InterfaceServiceImpl;

/**
 * cglib动态代理测试类
 */
public class CglibDynamicProxyClient {
    public static void main(String[] args) {
        /**动态代理 类 测试*/
        CglibDynamicProxy dynamicProxy = new CglibDynamicProxy();
        ClazzService clazzServiceProxy = (ClazzService) dynamicProxy.newInstance(ClazzService.class);
        String name = clazzServiceProxy.queryClassByName("abc");
        System.out.println("res:" + name);

        System.out.println("------------------------------------------------------------");
        /**动态代理 接口 测试*/
        InterfaceService interfaceServiceProxy = (InterfaceService) dynamicProxy.newInstance(InterfaceServiceImpl.class);
        String res = interfaceServiceProxy.queryServiceByName("qname");
        System.out.println("res:" + res);
    }
}
