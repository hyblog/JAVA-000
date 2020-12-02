package com.ipman.mysql.multipledatasource.annotation;

import java.lang.annotation.*;

/**
 * Created by ipipman on 2020/12/2.
 *
 * @version V1.0
 * @Package com.ipman.mysql.multipledatasource.annotation
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/12/2 4:31 下午
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DBMaster {
}
