package com.ipman.work05sb2.mybatis.impl;

import com.ipman.work05sb2.mybatis.IDemoService;
import com.ipman.work05sb2.mybatis.dao.DemoMapper;
import com.ipman.work05sb2.mybatis.model.Demo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ipipman on 2020/11/20.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.mybatis.impl
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/20 6:10 下午
 */
@Service
public class DemoServiceImpl implements IDemoService {

    @Resource
    private DemoMapper demoMapper;

    /**
     * Mybatis实践，根据ID查询数据
     *
     * @param id
     * @return
     */
    @Override
    public Demo findDemoById(Integer id) {
        return demoMapper.selectByPrimaryKey(id);
    }
}
