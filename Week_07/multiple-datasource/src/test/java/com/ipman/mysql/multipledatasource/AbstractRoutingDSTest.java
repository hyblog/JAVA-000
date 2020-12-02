package com.ipman.mysql.multipledatasource;

import com.ipman.mysql.multipledatasource.service.IDemoService;
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
public class AbstractRoutingDSTest {

    @Autowired
    private IDemoService demoService;

    @Test
    public void run(){
        demoService.testMasterSlave();
        demoService.testTransaction();
    }
}
