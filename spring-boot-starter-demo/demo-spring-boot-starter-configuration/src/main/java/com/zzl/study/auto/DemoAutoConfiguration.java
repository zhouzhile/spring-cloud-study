package com.zzl.study.auto;

import com.zzl.study.properties.DemoProperties;
import com.zzl.study.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DemoAutoConfiguration
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/8/10 17:08
 * @Version 1.0
 **/
@Configuration
@ConditionalOnProperty(value = "demo.name")
@EnableConfigurationProperties(DemoProperties.class)
public class DemoAutoConfiguration {

    @Autowired
    DemoProperties demoProperties;

    @Bean
    public DemoService demoService() {
        return new DemoService(demoProperties.getName(),demoProperties.getValue());
    }
}
