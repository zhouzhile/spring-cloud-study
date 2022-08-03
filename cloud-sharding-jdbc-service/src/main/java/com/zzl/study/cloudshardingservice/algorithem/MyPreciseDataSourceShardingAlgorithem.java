package com.zzl.study.cloudshardingservice.algorithem;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

/**
 * stabdard数据库分片策略：
 *    精准匹配策略算法（泛型 Long 表示分片键的数据类型）
 */
public class MyPreciseDataSourceShardingAlgorithem implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {

        // 获取到分片键的值
        Long cidValue = shardingValue.getValue();
        //实现 course_$->{cid%2+1)
        BigInteger shardingValueB = BigInteger.valueOf(cidValue);
        BigInteger resB = (shardingValueB.mod(new BigInteger("2"))).add(new BigInteger("1"));
        // 逻辑表和计算的值拼装 couse_1, course_2
        String key = "m"+resB;
        if(availableTargetNames.contains(key)){
            return key;
        }
        throw new UnsupportedOperationException("route "+ key +" is not supported ,please check your config");
    }
}
