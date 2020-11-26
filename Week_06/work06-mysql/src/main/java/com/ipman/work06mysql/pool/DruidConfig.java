package com.ipman.work06mysql.pool;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * Created by ipipman on 2020/11/26.
 *
 * @version V1.0
 * @Package com.ipman.work06mysql.pool
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/26 3:15 下午
 */
@Configuration
public class DruidConfig {

    //配置Druid链接池
    @Bean(name = "DruidPoolDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
