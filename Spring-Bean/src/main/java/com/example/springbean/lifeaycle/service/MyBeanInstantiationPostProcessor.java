package com.example.springbean.lifeaycle.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;

/**
 * @author zds
 * @Description bean实例化后置处理器
 * @createTime 2022/2/25 15:06
 */
public class MyBeanInstantiationPostProcessor implements InstantiationAwareBeanPostProcessor {
    //实例化前置
    //postProcessBeforeInstantiation方法返回如果为空的话，AbstractAutowireCapableBeanFactory的createBean方法将继续往下执行doCreateBean(创建bean)方法
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("userTest")){
            System.out.println("post process before " + beanName + " instantiation");
        }
        return null;
    }
    //实例化后置
    //如果返回false则不在执行postProcessProperties属性赋值
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userTest")){
            System.out.println("post process after " + beanName + " instantiation");
        }
        return true;
    }

    //autowire属性赋值
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (beanName.equals("userTest")){
            System.out.println("post process " + beanName + " Properties");
        }
        return pvs;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return null;
    }

    //BeanPostProcessor的子类，拥有初始化后置处理的方法
// 初始化前置
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userTest")){
            System.out.println("post process before" + beanName + " Initialization");
        }
        return bean;
    }
//初始化后置
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userTest")){
            System.out.println("post process after" + beanName + " Initialization");
        }
        return bean;
    }
}
