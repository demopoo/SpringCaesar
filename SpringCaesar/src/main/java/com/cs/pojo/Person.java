package com.cs.pojo;/** * @author: demopoo * @Date: Created in 下午12:51 2018/3/19 * @Des: * @Modifyed By: */public class Person {    private String name;    private int age;    public void speakVoice(){        System.out.println("人要说话");    }    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }    public int getAge() {        return age;    }    public void setAge(int age) {        this.age = age;    }    @Override    public String toString() {        return "Person{" +                "name='" + name + '\'' +                ", age=" + age +                '}';    }}