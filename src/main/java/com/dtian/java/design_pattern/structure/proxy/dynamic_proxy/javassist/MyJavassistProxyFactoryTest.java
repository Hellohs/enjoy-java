package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.javassist;

import com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.javassist.factory2.MyJavassistProxyFactory;
import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyJavassistProxyFactoryTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, CannotCompileException, NotFoundException, InvocationTargetException, IOException {
        //创建工厂
        MyJavassistProxyFactory myJavassistProxyFactory = new MyJavassistProxyFactory();
        //实现类
        final TestService targetClass = new TestServiceImpl();

        //InvocationHandler
        InvocationHandler invocationHandler = new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                //打印日志
                System.out.println("[before] The method " + methodName + " begins");
                Object result = null;
                //前置通知
                try{
                    //前置通知
                    result = method.invoke(targetClass, args);
                    //返回通知, 可以访问到方法的返回值
                    System.out.println(String.format("after method:%s execute", method.getName()));
                } catch (Exception e){
                    e.printStackTrace();
                    //异常通知, 可以访问到方法出现的异常
                    System.out.println(String.format("method:%s execute got exception:", method.getName()));
                }
                return result;
            }
        };

        TestService testService = myJavassistProxyFactory.getProxy(targetClass, invocationHandler);
        testService.test("test");
    }
}
