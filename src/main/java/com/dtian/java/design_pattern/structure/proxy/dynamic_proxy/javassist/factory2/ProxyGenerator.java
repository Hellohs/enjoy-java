package com.dtian.java.design_pattern.structure.proxy.dynamic_proxy.javassist.factory2;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 借助 javassist 创建 代理实例 工具类
 */
public class ProxyGenerator {
    //计数器
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private static final boolean SHOULD_BE_FINAL = true;
    private static final boolean SHOULD_BE_ABSTRACT = false;
    private static final boolean SHOULD_BE_PUBLIC = true;
    //代理实例缓存 容器
    private static final ConcurrentHashMap<Class<?>, Object> proxyInstanceCache = new ConcurrentHashMap<Class<?>, Object>();

    protected InvocationHandler invocationHandler;
    //构造函数
    protected ProxyGenerator(InvocationHandler invocationHandler) {
        this.invocationHandler = invocationHandler;
    }

    public static Object newProxyInstance(ClassLoader classLoader, Class<?> targetClass, InvocationHandler invocationHandler) throws NotFoundException, CannotCompileException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //判断缓存中是否存在
        if (proxyInstanceCache.containsKey(targetClass)) {
            return proxyInstanceCache.get(targetClass);
        }
        // singleton instance of classpool
        ClassPool classPool = ClassPool.getDefault();
        //生成代理类全限定名
        String qualifiedName = generateClassName(targetClass);
        System.out.println("qualifiedName:" + qualifiedName);
        //创建代理类
        CtClass proxy =classPool.makeClass(qualifiedName);
        setSuperClass(classPool, proxy);
        //获取被代理类的所有接口
        CtClass[] interfaces = classPool.get(targetClass.getName()).getInterfaces();
        int methodIndex = 0;
        for (CtClass parent : interfaces) {
            proxy.addInterface(parent);
            CtMethod[] methods = parent.getDeclaredMethods();
            for (int j = 0; j < methods.length; j++) {
                CtMethod method = methods[j];
                String fieldSrc = String.format(
                        "private static java.lang.reflect.Method method%d = Class.forName(\"%s\").getDeclaredMethods()[%d];",
                        methodIndex,
                        parent.getName(),
                        j);
                CtField field = CtField.make(fieldSrc, proxy);
                proxy.addField(field);
                // 生成对应的Method
                generateMethod(classPool, proxy, method, methodIndex);
                methodIndex++;
            }
        }
        //设置代理类的类修饰符
        setModifiers(proxy, SHOULD_BE_PUBLIC, SHOULD_BE_FINAL, SHOULD_BE_ABSTRACT);
        //生成构造方法
        generateConstructor(classPool, proxy);
        //持久化class到硬盘
        proxy.writeFile(".");
        //生成class
        Class<?> proxyClass = proxy.toClass(classLoader, null);
        //生成实例
        Object instance = proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);
        Object old = proxyInstanceCache.putIfAbsent(targetClass, instance);
        if(old != null){
            instance = old;
        }
        return instance;
    }

    private static void generateConstructor(ClassPool classPool, CtClass proxy) throws NotFoundException, CannotCompileException {
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{classPool.get(InvocationHandler.class.getName())}, proxy);
        String methodBodySrc = String.format("super(%s);", "$1");
        ctConstructor.setBody(methodBodySrc);
        proxy.addConstructor(ctConstructor);
    }

    private static void setModifiers(CtClass proxy, boolean shouldBePublic, boolean shouldBeFinal, boolean shouldBeAbstract) {
        int modifier = 0;
        modifier = shouldBePublic ? modifier | Modifier.PUBLIC : modifier;
        modifier = shouldBeFinal ? modifier | Modifier.FINAL : modifier;
        modifier = shouldBeAbstract ? modifier | Modifier.ABSTRACT : modifier;
        proxy.setModifiers(modifier);
    }

    private static void generateMethod(ClassPool classPool, CtClass proxy, CtMethod method, int methodIndex) throws NotFoundException, CannotCompileException {
        CtMethod ctMethod = new CtMethod(method.getReturnType(), method.getName(), method.getParameterTypes(), proxy);
        String methodBodySrc = String.format("return super.invocationHandler.invoke(this, method%d, $args);", methodIndex);
        ctMethod.setBody(methodBodySrc);
        proxy.addMethod(ctMethod);
    }

    private static void setSuperClass(ClassPool classPool, CtClass proxy) throws NotFoundException, CannotCompileException {
        proxy.setSuperclass(classPool.get(ProxyGenerator.class.getName()));
    }

    private static String generateClassName(Class<?> targetClass) throws NotFoundException {
        CtClass theInterface = null;
        for (CtClass parent : ClassPool.getDefault().get(targetClass.getName()).getInterfaces()) {
            if (theInterface == null) {
                theInterface = parent;
            }
            if (!Modifier.isPublic(parent.getModifiers())) {
                theInterface = parent;
                break;
            }
        }
        return theInterface.getPackageName() + ".$Proxy" + COUNTER.getAndIncrement();
    }
}
