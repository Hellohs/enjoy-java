package com.dtian.java.spring.bean_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * InstantiationAwareBeanPostProcessor
 * 接口本质是BeanPostProcessor的子接口，
 * 一般我们继承Spring为其提供的适配器类InstantiationAwareBeanPostProcessor Adapter来使用它，
 * 这个有3个方法，其中第二个方法postProcessAfterInitialization就是重写了BeanPostProcessor的方法。
 * 第三个方法postProcessPropertyValues用来操作属性，返回值也应该是PropertyValues对象。
 * 如下：
 */
public class MyInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessorAdapter]-postProcessBeforeInstantiation");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessorAdapter]-postProcessAfterInstantiation");
        return true;
    }

    //-----------------------------------------------------------------------------------------------------

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessorAdapter]-postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessorAdapter]-postProcessAfterInitialization");
        return bean;
    }

    //-----------------------------------------------------------------------------------------------------

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessorAdapter]-postProcessPropertyValues, bean:" + bean + ", beanName:" + beanName);
        return pvs;
    }
}
