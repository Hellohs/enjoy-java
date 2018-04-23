package com.dtian.java.design_pattern.structure.decorator;

/**
 * 被装饰者，定义将要接收附加责任的类
 */
public class ConcreteComponent extends Component{
    @Override
    void operation() {
        System.out.println("concrete component operation");
    }
}
