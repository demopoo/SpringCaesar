package com.cs.ioc;/** * @author: demopoo * @Date: Created in 下午1:44 2018/3/19 * @Des: * @Modifyed By: */public class AutowireCapableBeanFactory extends AbstractBeanFactory {    @Override    public BeanDefinition createBean(BeanDefinition beanDefinition) {        if (beanDefinition != null){            try {                Object object = beanDefinition.getBeanClass().newInstance();                beanDefinition.setBean(object);            } catch (InstantiationException e) {                e.printStackTrace();            } catch (IllegalAccessException e) {                e.printStackTrace();            }        }        return beanDefinition;    }}