package com.dtian.java.design_pattern.behavior.template;

/**
 * 模板方法模式
 * 抽象父类 父类定义行为，子类实现
 *
 * 定义：
 *     Define the skeleton of an algorithm in an operation,
 *     deferring some steps to subclasses.
 *     Template Method lets subclasses redefine certain steps of
 *     an algorithm without changing the algorithm's structure.
 *     （定义一个操作中的算法的框架，而将一些步骤延迟到子类中。
 *     使得子类可以不改变一个算法的结构
 *     即可重定义该算法的某些特定步骤。）
 *
 * 角色：
 *     基本方法：由子类实现，也可由父类自己实现，在模板方法中被调用
 *     模板方法：可以有一个或者有几个，一般是具体的方法，也就是一个框架，
 *     实现对基本方法的调度，完成固定的逻辑。
 *     一般被定义成final，防止被修改
 * 优点：
 *     封装不变部分，扩展可变部分
 *     提取公共部分代码，便于维护
 *     行为由父类控制，子类实现
 * 缺点：
 *     按照我们的设计习惯，抽象类负责声明最抽象、
 *     最一般的事物属性和方法，实现类完成具体的事物属性和方法。
 *     但是模板方法模式却颠倒了，抽象类定义了部分抽象方法，
 *     由子类实现，子类执行的结果影响了父类的结果，
 *     也就是子类对父类产生了影响，
 *     这在复杂的项目中，会带来代码阅读的难度，
 *     而且也会让新手产生不适感。
 * 注意：
 *     为了防止恶意的操作，一般模板方法都加上final关键字，不允许被覆写。
 */
public abstract class AbstractClass {

    /**
     * 父类基本方法
     */
    private final void concreteMethod() {
        System.out.println("AbstractClass concreteMethod");
    }

    /**
     * 基本方法，需要子类实现
     */
    protected abstract void abstractMethod();

    /**
     * 钩子函数，需要子类实现
     */
    protected void hookMethod() {
    }

    ;

    /**
     * 带判断的钩子函数，需要子类重写
     */
    protected boolean hookFlag() {
        return true;
    }

    /**
     * 模板方法，定义一个执行逻辑
     */
    public final void templateMethod() {
        abstractMethod();
        hookMethod();
        if (hookFlag()) {//子类的执行结果，影响父类是行为
            concreteMethod();
        }
    }
}
