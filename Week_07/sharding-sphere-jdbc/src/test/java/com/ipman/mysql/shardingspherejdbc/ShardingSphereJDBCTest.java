package com.ipman.mysql.shardingspherejdbc;

import com.ipman.mysql.shardingspherejdbc.service.IDemoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by ipipman on 2020/12/2.
 *
 * @version V1.0
 * @Package com.ipman.mysql.multipledatasource
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/2 4:48 下午
 */
@SpringBootTest
@Slf4j
public class ShardingSphereJDBCTest {

    @Autowired
    private IDemoService demoService;

    @Test
    public void run(){
        log.info("//测试读写分离");
        demoService.testMasterSlave();
        
        log.info("//测试读写分离+事务");
        demoService.testTransaction();
    }
}
