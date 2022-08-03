package com.zzl.study.cloudshardingservice.algorithem;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * stabdard数据库分片策略：
 *    范围匹配策略算法（泛型 Long 表示分片键的数据类型）
 */
public class MyRangeDataSourceShardingAlgorithem implements RangeShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        //select * from course where cid between 1 and 100;
        Long upperVal = rangeShardingValue.getValueRange().upperEndpoint();//100
        Long lowerVal = rangeShardingValue.getValueRange().lowerEndpoint();//1

        String logicTableName = rangeShardingValue.getLogicTableName();
        return Arrays.asList("m1","m2");
    }
}
