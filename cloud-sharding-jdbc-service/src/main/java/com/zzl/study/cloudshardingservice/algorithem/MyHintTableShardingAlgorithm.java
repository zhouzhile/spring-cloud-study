package com.zzl.study.cloudshardingservice.algorithem;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * @ClassName MyHintTableShardingAlgorithm
 * @Desc TODO
 * @Author Lenovo
 * @Date 2022/5/31 13:51
 * @Version 1.0
 **/
public class MyHintTableShardingAlgorithm implements HintShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, HintShardingValue shardingValue) {
        String key = shardingValue.getLogicTableName() + "_" + shardingValue.getValues().toArray()[0];
        if(availableTargetNames.contains(key)){
            return Arrays.asList(key);
        }
        throw new UnsupportedOperationException("route "+ key +" is not supported ,please check your config");
    }
}
