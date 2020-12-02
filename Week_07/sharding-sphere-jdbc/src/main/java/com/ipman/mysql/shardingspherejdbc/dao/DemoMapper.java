package com.ipman.mysql.shardingspherejdbc.dao;

import com.ipman.mysql.shardingspherejdbc.model.Demo;

public interface DemoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Demo record);

    int insertSelective(Demo record);

    Demo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Demo record);

    int updateByPrimaryKey(Demo record);
}