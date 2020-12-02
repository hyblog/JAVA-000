package com.ipman.mysql.concurrentdml.dao;

import com.ipman.mysql.concurrentdml.model.TOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    TOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKeyWithBLOBs(TOrder record);

    int updateByPrimaryKey(TOrder record);

    void multipleInsertByList(@Param("orderList") List<TOrder> orderList);
}