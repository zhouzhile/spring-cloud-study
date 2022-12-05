package com.zzl.study.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName DemoProperties
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/8/10 17:09
 * @Version 1.0
 **/
@ConfigurationProperties("demo")
public class DemoProperties {

    private String name;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
