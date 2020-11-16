package com.springboot.frame.ioc.ann;

import org.springframework.stereotype.Component;

/**
 * Created by ipipman on 2020/11/16.
 *
 * @version V1.0
 * @Package com.springboot.frame.ioc.ann
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/16 11:25 下午
 */
@Component
public class Teacher {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                '}';
    }
}
