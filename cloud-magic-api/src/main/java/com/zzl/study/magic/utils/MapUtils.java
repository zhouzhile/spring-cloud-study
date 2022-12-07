package com.zzl.study.magic.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.ssssssss.magicapi.core.annotation.MagicModule;
import org.ssssssss.magicapi.core.config.MagicFunction;
import org.ssssssss.script.annotation.Comment;

/**
 * @ClassName MapUtils
 * @Desc 高德地图Util
 * @Author Lenovo
 * @Date 2022/12/3 15:03
 * @Version 1.0
 **/
@Slf4j
@Component
@MagicModule("mapUtils") // 将这个类作为Magic的一个模块处理，在方法上加上@Comment注解，就可以在magicApi的页面上直接调用这个方法了
@RequiredArgsConstructor
public class MapUtils implements MagicFunction {

    /**
     * 使用@RequiredArgsConstructor注解+final的注入方式
     */
    private final RestTemplate restTemplate;
    /**
     * 高德地图的key，在【高德开放平台】创建应用自动生成
     */
    private static String key = "7ac082358d0e52c7651bb58783d33f31";
    /**
     * 高德提供的接口
     */
    private static String GD_URL = "https://restapi.amap.com/v3/geocode/geo?address=%s&key=%s";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将地址转换为经纬度信息
     *
     * @param address
     * @return
     */
    @Comment("将地址转换为经纬度信息")
    public String addressToLocation(String address){
        String location="";
        // 补齐高德提供的接口参数
        String allUrl = String.format(GD_URL,address,key);
        // 调用高德接口
        ResponseEntity<String> response = restTemplate.getForEntity(allUrl, String.class);
        // 分析接口返回参数
        if (HttpStatus.OK == response.getStatusCode()) {
            location = getLocation(response);
        }else {
            log.info("调用高德接口失败");
        }
        return location;
    }

    @Comment("根据经纬度判断所在地市")
    public String getCoordinate(Double lng, Double lat) {
        String url = "https://restapi.amap.com/v3/geocode/regeo?location=%s&key=%s&radius=1000&extensions=all";
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(url, lng + "," + lat, key), String.class);
        String address = getAddress(response);
        return address;
    }

    private String getAddress(ResponseEntity<String> response){
        String address="";
        try {
            JsonNode jsonNode = MAPPER.readValue(response.getBody(), JsonNode.class);
            if (jsonNode != null
                    && "1".equals(jsonNode.get("status").asText())
                    && !jsonNode.get("regeocode").isEmpty()) {
                address = jsonNode.get("regeocode").get("formatted_address") .asText();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return address;
    }

    /**
     * 解析响应的实体
     *
     * @param response
     * @return
     */
    private String getLocation(ResponseEntity<String> response) {
        try {
            JsonNode jsonNode = MAPPER.readValue(response.getBody(), JsonNode.class);
            if (jsonNode != null
                    && "1".equals(jsonNode.get("status").asText())
                    && !jsonNode.get("geocodes").isEmpty()) {
                return jsonNode.get("geocodes").get(0).get("location").asText();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
