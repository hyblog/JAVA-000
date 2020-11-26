package com.ipman.work06mysql.pool;

import org.apache.commons.dbcp2.BasicDataSource;
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
 * @date 2020/11/26 6:35 下午
 */
@Configuration
public class DBCPConfig {

    //配置DBCP链接池
    @Bean(name = "DBCPPoolDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dbcp2")
    public DataSource DBCPDataSource() {
        DataSource dataSource = new BasicDataSource();
        return dataSource;
    }
}
