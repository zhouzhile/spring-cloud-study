package com.zzl.study.cloudshardingservice.entry;

/**
 * @ClassName Course
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/5/30 13:57
 * @Version 1.0
 **/
public class Course {

    private Long cid;
    private String cname;
    private Long userId;
    private String cstatus;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", userId=" + userId +
                ", cstatus='" + cstatus + '\'' +
                '}';
    }
}
