package com.dtian.java.spring.bean_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * bean Student
 */
public class Student implements InitializingBean, BeanNameAware, BeanFactoryAware, DisposableBean {
    String name;
    int age;

    /**
     * 实现了BeanNameAware接口，Spring可以将BeanName注入该属性中
     */
    private String beanName;

    private BeanFactory beanFactory;

    public Student() {
        System.out.println("[Student]-Constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("[Student]-setter, setName:" + name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        System.out.println("[Student]-setter, setAge:" + age);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[InitializingBean]-afterPropertiesSet");
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        System.out.println("[BeanNameAware]-setBeanName, beanName:" + beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("[BeanFactoryAware]-setBeanFactory, beanFactory:" + beanFactory);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("[DisposableBean]-destroy");
    }

    public void initMethod() {
        System.out.println("[XML]-init-method");
    }

    public void desMethod() {
        System.out.println("[XML]-destroy-method");
    }
}
