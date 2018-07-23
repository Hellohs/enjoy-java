package com.dtian.java.core_java.jvm.class_load;

public class HelloClass {
    String name;

    public HelloClass() {
        this.name = "rose";
    }

    public void hello() {
        System.out.println("hello " + name + ", i'm load by：" + getClass().getClassLoader());
    }
}
