package com.ipman.work05sb2.cache;

import java.lang.annotation.*;

/**
 * Created by ipipman on 2020/11/20.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.cache
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/20 7:48 下午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyCacheAnn {

    //缓存保留时间
    int expireSecond() default 0;

}
