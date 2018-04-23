package com.dtian.java.design_pattern.structure.decorator;

/**
 * 装饰者模式：在不改变原类文件以及不使用继承的情况下(合理避免类爆炸式增长)，
 * 动态地将责任附加到对象上，从而实现动态拓展一个对象的功能。
 * 在java IO中有广泛的应用
 */

/**
 * 抽象角色，一般是一个接口或者一个抽象类，用来定义属性或者方法
 */
public abstract class Component {
    abstract void operation();
}
