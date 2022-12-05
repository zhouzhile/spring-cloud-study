package com.zzl.study.service;


/**
 * @ClassName DemoService
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/8/10 17:17
 * @Version 1.0
 **/
public class DemoService {

    private String name;
    private String value;

    public DemoService(String name, String value) {
        this.name = name;
        this.value = value;
        System.out.println("DemoService init success!");
    }

    public String getName() {
        return name;
    }

    public DemoService setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public DemoService setValue(String value) {
        this.value = value;
        return this;
    }

    public void say() {
        System.out.println(name + " say: " + value);
    }
}
