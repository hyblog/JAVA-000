package com.springboot.frame.ioc.ann;

import com.springboot.frame.ioc.xml.Animal;
import com.springboot.frame.ioc.xml.Dog;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by ipipman on 2020/11/16.
 *
 * @version V1.0
 * @Package com.springboot.frame.ioc.ann
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/16 11:24 下午
 */
@Component
public class MyCat implements FactoryBean<Animal> {

    /**
     * 返回具体的工厂Bean对象
     *
     * @return
     * @throws Exception
     */
    @Override
    public Animal getObject() throws Exception {
        return new Dog();
    }

    /**
     * 获取Bean类型
     *
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Animal.class;
    }

}
