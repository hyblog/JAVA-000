package com.ipman.work06mysql.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
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
 * @date 2020/11/26 7:15 下午
 */
@Configuration
public class HikariCPConfig {

    //配置HiKariCP链接池
    @Bean(name = "HikariCPPoolDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource HikariCPDataSource() {
        DataSource dataSource = new HikariDataSource();
        return dataSource;
    }
}
