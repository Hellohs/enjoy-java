package com.dtian.java.spring.bean_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * bean 后置处理器
 * 如果我们想在Spring容器中完成bean实例化、
 * 配置以及其他初始化方法前后要添加一些自己逻辑处理。
 * 我们需要定义一个或多个BeanPostProcessor接口实现类，
 * 然后注册到Spring IoC容器中。
 */

/**
 * BeanPostProcessor接口有两个回调方法。
 * 当一个BeanPostProcessor的实现类注册到Spring IOC容器后，
 * 对于该Spring IOC容器所创建的每个bean实例在初始化方法
 * （如afterPropertiesSet和任意已声明的init方法）调用前，
 * 将会调用BeanPostProcessor中的postProcessBeforeInitialization方法，
 * 而在bean实例初始化方法调用完成后，
 * 则会调用BeanPostProcessor中的postProcessAfterInitialization方法
 */

/**
 * --> Spring IOC容器实例化Bean
 * --> 调用BeanPostProcessor的postProcessBeforeInitialization方法
 * --> 调用bean实例的初始化方法
 * --> 调用BeanPostProcessor的postProcessAfterInitialization方法
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        System.out.println("[MyBeanPostProcessor]-Constructor");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[MyBeanPostProcessor]-postProcessBeforeInitialization, beanName:" + beanName + ", 可以对bean属性进行修改");
        if(bean instanceof Student){
            ((Student)bean).setName("BeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[MyBeanPostProcessor]-postProcessAfterInitialization, beanName:" + beanName + ", 可以对bean属性进行修改");
        if(bean instanceof Student){
            ((Student)bean).setName("AfterInitialization");
        }
        return bean;
    }
}
