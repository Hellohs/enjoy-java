package com.dtian.java.core_java.jvm.class_load;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassLoader加载测试
 * 自定义类加载器
 * 在 {@link java.lang.ClassLoader} 中
 * loadClass 逻辑是
 * 如果 类加载器 parent不为空，则从父类加载
 * 否则 从findBootstrapClassOrNull
 * 如果上面二个都没有返回 索要的Class信息，
 * 那么 去 findClass
 * <p>
 * 在java中，类的加载采用双亲委派机制，即：
 * 如果一个类加载器收到类加载的请求，它首先不会自己去尝试加载这个类，而是把这个请求委派给父类加载器完成。
 * 每个类加载器都是如此，只有当父加载器在自己的搜索范围内找不到指定的类时（即ClassNotFoundException），子加载器才会尝试自己去加载。
 */
public class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        /**直接调用 findClass*/
        return findClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        /** 获得本类的绝对路径*/
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        InputStream is = getClass().getResourceAsStream(fileName);
        if(is != null){
            /**本包下的类，由自定义类加载器 加载，否则交给父类加载*/
            byte[] data = getClassBytes(is);
            /**获取class*/
            return defineClass(name, data, 0, data.length);
        }
        return super.loadClass(name);
    }

    /**
     * 从获取.class文件的二进制
     *
     * @param is
     * @return .class文件二进制
     */
    private byte[] getClassBytes(InputStream is) {
        try {
            byte[] data = new byte[is.available()];
            is.read(data);
            is.close();
            is = null;
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 自定义类加载器测试
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MyClassLoader mcl = new MyClassLoader();
        Class clazz = mcl.loadClass("com.dtian.java.core_java.jvm.class_load.HelloClass");
        /** 创建instance */
        Object o1 = clazz.newInstance();
        /** ClassLoader */
        System.out.println("o1 ClassLoader:" + o1.getClass().getClassLoader());
        /**返回false，因为类加载器不同*/
        System.out.println(o1 instanceof com.dtian.java.core_java.jvm.class_load.HelloClass);
        /**调用方法*/
        o1.getClass().getDeclaredMethod("hello").invoke(o1, null);

        /**AppClassLoader加载器*/
        System.out.println("---------------------------------------------------------");
        ClassLoader cl = MyClassLoader.class.getClassLoader();
        Object o2 = cl.loadClass("com.dtian.java.core_java.jvm.class_load.HelloClass").newInstance();
        System.out.println("o2 ClassLoader:" + o2.getClass().getClassLoader());
        /** 返回true */
        System.out.println(o2 instanceof com.dtian.java.core_java.jvm.class_load.HelloClass);
    }
}
