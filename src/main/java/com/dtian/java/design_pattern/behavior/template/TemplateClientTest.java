package com.dtian.java.design_pattern.behavior.template;

/**
 * 模板方法模式测试类
 */
public class TemplateClientTest {
    public static void main(String[] args) {
        AbstractClass clza = new ConcreteClassA();
        clza.templateMethod();

        System.out.println("-- -- -- -- -- -- -- -- -- -- --");
        AbstractClass clzb = new ConcreteClassB();
        clzb.templateMethod();
    }
}
