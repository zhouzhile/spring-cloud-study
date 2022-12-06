package com.zzl.study.magic.service.impl;

import com.zzl.study.magic.service.PersonService;
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
}
