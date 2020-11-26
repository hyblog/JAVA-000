package com.ipman.work06mysql.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
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
 * @date 2020/11/26 7:00 下午
 */
@Configuration
public class C3P0Config {

    //配置C3P0链接池
    @Bean(name = "C3P0PoolDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.c3p0")
    public DataSource C3P0DataSource() {
        DataSource dataSource = new ComboPooledDataSource();
        return dataSource;
    }
}
