package com.zzl.study.magic.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.ssssssss.magicapi.datasource.model.MagicDynamicDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @ClassName DbConfig
 * @Desc zhouhile
 * @Author Lenovo
 * @Date 2022/11/10 15:58
 * @Version 1.0
 **/
@Configuration
public class DbConfig {

    @Autowired
    private DynamicDataSourceProperties properties;

    /**
     * 配置 Oracle 主键生成器
     *
     * @return OracleKeyGenerator
     */
    @ConditionalOnMissingBean
    @Bean
    public OracleKeyGenerator getOracleKeyGenerator() {
        return new OracleKeyGenerator();
    }

    /**
     * 将application配置文件种的多个数据源，加载到DynamicDataSourceProvider
     *
     * @return
     */
    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        Map<String, DataSourceProperty> datasourceMap = properties.getDatasource();
        return new AbstractDataSourceProvider() {
            @Override
            public Map<String, DataSource> loadDataSources() {
                return createDataSourceMap(datasourceMap);
            }
        };
    }

    /**
     * 配置 magic-api 动态数据源
     */
    @Bean
    public MagicDynamicDataSource magicDynamicDataSource(DynamicDataSourceProvider dynamicDataSourceProvider){
        MagicDynamicDataSource dynamicDataSource = new MagicDynamicDataSource();
        Map<String, DataSource> map = dynamicDataSourceProvider.loadDataSources();
        dynamicDataSource.setDefault(map.get(properties.getPrimary()));
        map.forEach(dynamicDataSource::add);
        return dynamicDataSource;
    }

    /**
     * 将动态数据源设置为首选的
     * 当spring存在多个数据源时, 自动注入的是首选的对象
     */
    @Primary
    @Bean
    public DataSource dataSource(DynamicDataSourceProvider dynamicDataSourceProvider) {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        // 设置在yml文件里配置primary数据源
        dataSource.setPrimary(properties.getPrimary());
        dataSource.setStrict(properties.getStrict());
        dataSource.setStrategy(properties.getStrategy());
        dataSource.setProvider(dynamicDataSourceProvider);
        dataSource.setP6spy(properties.getP6spy());
        dataSource.setSeata(properties.getSeata());
        return dataSource;
    }
}
