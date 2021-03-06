package com.ipman.mysql.multipledatasource.service.impl;

import com.ipman.mysql.multipledatasource.dao.DemoMapper;
import com.ipman.mysql.multipledatasource.model.Demo;
import com.ipman.mysql.multipledatasource.service.IDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ipipman on 2020/12/2.
 *
 * @version V1.0
 * @Package com.ipman.mysql.multipledatasource.service.impl
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/2 4:41 下午
 */
@Service
@Slf4j
public class DemoService implements IDemoService {

    @Resource
    private DemoMapper demoMapper;

    //测试读写分离
    @Override
    public void testMasterSlave() {
        //DML
        Demo demo = new Demo();
        demo.setName("ipman");
        demoMapper.insertSelective(demo);

        demo.setId(11);
        demo.setName("ipipman");
        demoMapper.updateByPrimaryKey(demo);

        //DQL
        demo = demoMapper.selectByPrimaryKey(11);
        System.out.println(demo);

        //DQL
        demo = demoMapper.selectByPrimaryKey(11);
        System.out.println(demo);
    }

    //测试主从事务
    @Override
    @Transactional("transactionManager")
    public void testTransaction() {
        try {
            //DML
            Demo demo = new Demo();
            demo.setName("ipman");
            demoMapper.insertSelective(demo);

            demo.setId(11);
            demo.setName("ipipipman");
            demoMapper.updateByPrimaryKey(demo);

            //DQL
            demo = demoMapper.selectByPrimaryKey(11);
            System.out.println(demo);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
