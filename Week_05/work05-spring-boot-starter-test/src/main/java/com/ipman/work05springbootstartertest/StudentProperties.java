package com.ipman.work05springbootstartertest;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by ipipman on 2020/11/19.
 *
 * @version V1.0
 * @Package com.ipman.work05springbootstartertest
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/19 6:58 下午
 */
@ConfigurationProperties(prefix = "custom.boot.starter.student")
public class StudentProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
