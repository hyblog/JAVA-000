package com.springboot.frame.proxy.statics;

import java.io.Serializable;

/**
 * Created by ipipman on 2020/11/17.
 *
 * @version V1.0
 * @Package com.springboot.frame.proxy.statics
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/17 3:14 下午
 */

public class Person implements IPerson, Serializable {

    @Override
    public void doing() {
        System.out.println("Person.doing() .... ");
    }
}
