package com.zzl.study.cloudshardingservice.algorithem;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Complex表分配策略
 * 表匹配，精准匹配策略
 * 泛型 Long 表示分片键的数据类型
 */
public class MyComplexTableShardingAlgorithem implements ComplexKeysShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> shardingValue) {
        Range<Long> cidRange = shardingValue.getColumnNameAndRangeValuesMap().get("cid");
        Collection<Long> userIdCol = shardingValue.getColumnNameAndShardingValuesMap().get("user_id");

        Long upperVal = 0L;
        Long lowerVal = 0L;
        if (cidRange != null){
            upperVal = cidRange.upperEndpoint();
            lowerVal = cidRange.lowerEndpoint();
        }

        List<String> res = new ArrayList<>();

        for(Long userId: userIdCol){
            System.out.println(userId);
            //course_{userID%2+1}
            BigInteger userIdB = BigInteger.valueOf(userId);
            BigInteger target = (userIdB.mod(new BigInteger("2"))).add(new BigInteger("1"));

            res.add(shardingValue.getLogicTableName()+"_"+target);
        }

        return res;
    }
}
