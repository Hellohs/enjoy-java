package com.dtian.java.design_pattern.structure.decorator;

/**
 * 装饰者：持有一个抽象父类的引用，并定义一个与抽象父类一致的接口。
 */
public class Decorator extends Component {
    /**拥有一个基类的引用*/
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    void operation() {
        /**不做操作，直接使用基类引用去完成操作*/
        component.operation();
    }
}
