package com.zzl.study.magic.db.controller;

import com.zzl.study.magic.callback.MyCallBack;
import com.zzl.study.magic.db.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PersonController
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/12/14 9:06
 * @Version 1.0
 **/
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/byType")
    public void handleByType(int type){
        personService.handleByType(type, new MyCallBack() {
            @Override
            public void onSuccess(String str) {
                System.out.println("success");
            }

            @Override
            public void onException() {
                System.out.println("fail");
            }
        });
    }
}
