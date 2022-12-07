package com.zzl.study.magic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.ssssssss.script.annotation.Comment;

/**
 * 请求模板配置类
 * <p>
 * 文件名称：{@link RequestTempleConfig}
 * <p>
 * <b>
 * Creation Time: 2022/6/20
 *
 * @author lyw
 * @version 1.0.0.0
 * @since 1.0.0.0
 **/
@Configuration
public class RequestTempleConfig{

    @Bean
    @Comment("创建RestTemplate对象")
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public SimpleClientHttpRequestFactory getSimpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory=new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(50000);
        factory.setReadTimeout(100000);
        return factory;
    }
}
