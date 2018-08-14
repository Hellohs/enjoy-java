package com.dtian.java.design_pattern.created.factory.abstract_factory;

/**
 * 在工厂方法模式中，一个工厂只能生产一种产品，
 * 但是在现实生活中，往往一个工厂会有好几个产品线。
 *
 * 抽象工厂的定义：
 *     为创建一组相关或相互依赖的对象提供一个接口，而且无须指定它们的具体类。
 * 好比是相互影响的业务品种，业务分类，能够共同继承或者实现一个抽象类或接口。
 *
 * 所以：对于抽象工厂，一个工厂会生产多个相互有关联的（能够共同继承或者实现一个抽象类或接口）产品
 *    有N个产品族，在抽象工厂类中就应该有N个创建方法
 */
public interface AbstractFactory {

    AbstractProductA createProductA();
    AbstractProductB createProductB();
}
