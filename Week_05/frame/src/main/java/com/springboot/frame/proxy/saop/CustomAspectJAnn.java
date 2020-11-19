package com.springboot.frame.proxy.saop;

import java.lang.annotation.*;

/**
 * Created by ipipman on 2020/11/17.
 *
 * @version V1.0
 * @Package com.springboot.frame.proxy.aspectjs
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/17 6:18 下午
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomAspectJAnn {

    int cacheExpire() default 0;

}