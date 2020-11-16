package com.springboot.frame.ioc.ann;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by ipipman on 2020/11/16.
 *
 * @version V1.0
 * @Package com.springboot.frame.ioc.xml
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/16 11:20 下午
 */
@Service
public class Worker {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}