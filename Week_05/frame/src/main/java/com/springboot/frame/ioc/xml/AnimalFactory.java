package com.springboot.frame.ioc.xml;

/**
 * Created by ipipman on 2020/11/16.
 *
 * @version V1.0
 * @Package com.springboot.frame.ioc.xml
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/16 11:05 下午
 */
public class AnimalFactory {

    public Animal getAnimal(String type) {
        if ("dog".equals(type)) {
            return new Dog();
        } else {
            return new Cat();
        }
    }
}
