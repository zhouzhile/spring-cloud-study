package com.zzl.study.startertest;

import com.zzl.study.service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StarterTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(StarterTestApplication.class, args);
        DemoService demoService = (DemoService)run.getBean("demoService");
        demoService.say();
    }

}
