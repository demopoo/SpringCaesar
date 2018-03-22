package com.cs.ioc;import java.lang.reflect.Field;import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.List;/** * @author: demopoo * @Date: Created in 下午1:44 2018/3/19 * @Des: 创建Bean * @Modifyed By: */public class AutowireCapableBeanFactory extends AbstractBeanFactory {    @Override    public Object createBean(BeanDefinition beanDefinition) {        Object object = null;        if (beanDefinition != null){            try {                object = beanDefinition.getBeanClass().newInstance();                List<PropertyItem> list = beanDefinition.getPropertyItems().list;                for (PropertyItem propertyItem : list){                    Field field = object.getClass().getDeclaredField(propertyItem.getPropertyName());                    field.setAccessible(true);                    String fieldName = field.getType().getSimpleName();                    Object propertyVal = propertyItem.getPropertyObject();                    try {                        if ("int".equalsIgnoreCase(fieldName)){                            propertyVal = Integer.parseInt(propertyVal.toString());                        }else if ("double".equalsIgnoreCase(fieldName)){                            propertyVal = Double.parseDouble(propertyVal.toString());                        }else if ("date".equalsIgnoreCase(fieldName)){                            propertyVal = new SimpleDateFormat("yyyy-MM-dd").parse(propertyVal.toString());                        }else {                            if (propertyVal instanceof BeanRefrence){                                BeanRefrence beanRefrence = (BeanRefrence)propertyVal;                                propertyVal = getBean(beanRefrence.getBeanName());                            }                        }                    }catch (ParseException ex){                        ex.printStackTrace();                    }                    field.set(object,propertyVal);                }            } catch (InstantiationException e) {                e.printStackTrace();            } catch (IllegalAccessException e) {                e.printStackTrace();            } catch (NoSuchFieldException e) {                e.printStackTrace();            }        }        return object;    }}