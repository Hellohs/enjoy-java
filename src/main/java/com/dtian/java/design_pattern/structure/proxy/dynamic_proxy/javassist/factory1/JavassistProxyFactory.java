package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.javassist.factory1;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 使用Javassist 动态代理工厂 动态生成代理类
 *   Javassist是一个开源的分析、编辑和创建Java字节码的类库。
 *   javassist是jboss的一个子项目，其主要的优点，在于简单，而且快速。
 *   直接使用java编码的形式，而不需要了解虚拟机指令，就能动态改变类的结构，或者动态生成类。
 *
 * 与 ASM 动态生成代码相比,javassist更加简单，而ASM对开发人员的要求较高
 * 就性能上而言，
 *   在生成代码时，jdk创建代理 快于 cglib和javassist
 *   在调用时，cglib和javassist性能优于jdk 创建动态代理的方式
 */
public class JavassistProxyFactory {

    /**
     * proxy工厂创建动态代理类
     * @param type
     * @return 动态代理类
     */
    public Object getProxy(Class<?> type) throws IllegalAccessException, InstantiationException {
        //创建工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        //设置父类
        proxyFactory.setSuperclass(type);
        //过滤finalize方法
        proxyFactory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                return !m.getName().equalsIgnoreCase("finalize");
            }
        });

        //动态创建class
        Class c = proxyFactory.createClass();
        //创建代理类实例
        Object proxy = c.newInstance();
        ((Proxy) proxy).setHandler(new MethodHandler() {
            @Override
            public Object invoke(Object self, Method method, Method proceed, Object[] args) throws Throwable {
                System.out.println("method invoke before");
                Object res = proceed.invoke(self, args);
                System.out.println("method invoke after");
                return res;
            }
        });
        return proxy;
    }
}
