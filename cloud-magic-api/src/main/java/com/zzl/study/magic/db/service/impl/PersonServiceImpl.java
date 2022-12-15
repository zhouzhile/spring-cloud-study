package com.zzl.study.magic.db.service.impl;

import com.zzl.study.magic.callback.MyCallBack;
import com.zzl.study.magic.db.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @ClassName PersonServiceImpl
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/11/10 17:43
 * @Version 1.0
 **/
@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public String say() {
        return "magic-api初体验";
    }

    @Override
    public void handleByType(Integer type, MyCallBack myCallBack) {
        if (type == 1){
            myCallBack.onSuccess("回调需要的参数，根据自己的业务决定");
        }else {
            myCallBack.onException();
        }
    }
}
