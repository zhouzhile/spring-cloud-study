package com.zzl.study.cloudshardingservice.algorithem;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * stabdard表分片策略：
 *    精准匹配策略算法（泛型 Long 表示分片键的数据类型）
 */
public class MyRangeTableShardingAlgorithem implements RangeShardingAlgorithm<Long> {

    /**
     * 返回可能出现的结果集合
     *
     * @param collection
     * @param rangeShardingValue
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        // 最大值
        Long maxLong = rangeShardingValue.getValueRange().upperEndpoint();
        // 最小值
        Long minLong = rangeShardingValue.getValueRange().lowerEndpoint();

        String logicTableName = rangeShardingValue.getLogicTableName();
        return Arrays.asList(logicTableName+"_1",logicTableName+"_2");
    }
}
