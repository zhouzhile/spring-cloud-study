package com.zzl.study.cloudshardingservice;

import com.zzl.study.cloudshardingservice.callback.ContionCallBack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName CallBackTest
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/6/1 15:31
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CallBackTest {

    public void add(boolean tag, ContionCallBack callBack){
        if (tag){
            callBack.onReqSuccess("成功了");
        }else {
            callBack.onReqFailed("失败了");
        }
    }

    @Test
    public void test(){
        add(true, new ContionCallBack() {
            @Override
            public void onReqSuccess(String result) {
                System.out.println(result);
            }
            @Override
            public void onReqFailed(String errorMsg) {
                System.out.println(errorMsg);
            }
        });
    }
}
