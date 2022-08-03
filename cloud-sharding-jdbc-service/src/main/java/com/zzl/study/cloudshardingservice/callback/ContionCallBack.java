package com.zzl.study.cloudshardingservice.callback;

/**
 * @ClassName ContionCallBack
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/1 15:36
 * @Version 1.0
 **/
public interface ContionCallBack {
    void onReqSuccess(String result);
    void onReqFailed(String errorMsg);
}
