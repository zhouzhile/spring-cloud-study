package com.zzl.study.cloudnettyservice.code.pojo;

/**
 * @ClassName User
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/15 14:49
 * @Version 1.0
 **/
public class User {

    private Integer id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
