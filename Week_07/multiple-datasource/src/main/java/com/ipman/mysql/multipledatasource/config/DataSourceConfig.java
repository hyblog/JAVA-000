package com.ipman.mysql.multipledatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.github.pagehelper.PageHelper;
import com.ipman.mysql.multipledatasource.bean.DBRouingDataSource;
import com.ipman.mysql.multipledatasource.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;

/**
 * Created by ipipman on 2020/12/2.
 *
 * @version V1.0
 * @Package com.ipman.mysql.concurrentdml.config
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/2 11:33 上午
 */
@Configuration
@Slf4j
public class DataSourceConfig {

    //主
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "mybatis.master.datasource")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    //从1
    @Bean(name = "slave1DataSource")
    @ConfigurationProperties(prefix = "mybatis.slave1.datasource")
    public DataSource slave1DataSource() {
        return new DruidDataSource();
    }

    //从2
    @Bean(name = "slave2DataSource")
    @ConfigurationProperties(prefix = "mybatis.slave2.datasource")
    public DataSource slave2DataSource() {
        return new DruidDataSource();
    }

    //配置主从数据源路由
    @Bean(name = "routingDataSource")
    public DataSource routingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                            @Qualifier("slave1DataSource") DataSource slave1DataSource,
                                            @Qualifier("slave2DataSource") DataSource salve2DataSource) {
        DBRouingDataSource dbRouingDataSource = new DBRouingDataSource();
        //默认数据源
        dbRouingDataSource.setDefaultTargetDataSource(masterDataSource);
        //动态路由数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE1, slave1DataSource);
        targetDataSources.put(DBTypeEnum.SLAVE2, salve2DataSource);
        dbRouingDataSource.setTargetDataSources(targetDataSources);
        return dbRouingDataSource;
    }
}