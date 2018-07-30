package com.dtian.java.spring.bean_lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanLifeCycleTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println("student name:" + student.getName());

        /** 注销容器 */
        ((ClassPathXmlApplicationContext) context).registerShutdownHook();
    }
}
