package com.ipman.mysql.multipledatasource.bean;

import com.sun.istack.internal.Nullable;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by ipipman on 2020/12/2.
 *
 * @version V1.0
 * @Package com.ipman.mysql.multipledatasource.bean
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/2 4:16 下午
 */
public class DBRouingDataSource extends AbstractRoutingDataSource {

    //动态获取数据源
    @Override
    @Nullable
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
    
}
