package com.dtian.java.spring.bean_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * bean工厂后置处理器
 * Spring IoC容器允许
 * BeanFactoryPostProcessor在容器实例化任何bean之前读取bean的定义(配置元数据)，并可以修改它。
 * 同时可以定义多个BeanFactoryPostProcessor，
 * 通过设置'order'属性来确定各个BeanFactoryPostProcessor执行顺序。
 */

/**
 * BeanFactoryPostProcessor是生命周期中最早被调用的，
 * 远远早于BeanPostProcessor。
 * 它在spring容器加载了bean的定义文件之后，
 * 在bean实例化之前执行的。
 * 也就是说，
 * Spring允许BeanFactoryPostProcessor在容器创建bean之前读取bean配置元数据，并可进行修改。
 * 例如增加bean的属性和值，重新设置bean是否作为自动装配的侯选者，重设bean的依赖项等等。
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        System.out.println("[MyBeanFactoryPostProcessor]-Constructor");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("[MyBeanFactoryPostProcessor]-postProcessBeanFactory, beanFactory:" + beanFactory);
        BeanDefinition st = beanFactory.getBeanDefinition("student");
        /**会修改xml中 property 的定义*/
        st.getPropertyValues().addPropertyValue("name", "BeanFactoryPostProcessor_postProcessBeanFactory");
    }
}
