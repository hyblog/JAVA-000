package com.springboot.frame.config;

import com.springboot.frame.beans.HelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ipipman on 2020/11/11.
 *
 * @version V1.0
 * @Package com.springboot.frame.config
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/11 6:38 下午
 */
@Configuration
public class MyConfig {

    @Bean
    public HelloWorld world() {
        return new HelloWorld();
    }
}
