package com.ipman.mysql.multipledatasource.bean;

import com.ipman.mysql.multipledatasource.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ipipman on 2020/12/2.
 *
 * @version V1.0
 * @Package com.ipman.mysql.multipledatasource.bean
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/2 4:09 下午
 */
@Slf4j
public class DBContextHolder {

    //存储当前线程数据源类型
    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    //设置当前线程数据源类型
    public static void set(final DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    //返回当前线程数据源类型
    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        log.info("切换到master");
    }

    //Slave轮训策略
    public static void slave() {
        //  轮询
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DBTypeEnum.SLAVE1);
            log.info("切换到slave1");
        } else {
            set(DBTypeEnum.SLAVE2);
            log.info("切换到slave2");
        }
    }


}
