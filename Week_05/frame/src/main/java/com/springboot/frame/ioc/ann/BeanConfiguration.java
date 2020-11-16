package com.springboot.frame.ioc.ann;

import com.springboot.frame.ioc.xml.Animal;
import com.springboot.frame.ioc.xml.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ipipman on 2020/11/16.
 *
 * @version V1.0
 * @Package com.springboot.frame.ioc.ann
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/16 11:16 下午
 */
@Configuration
public class BeanConfiguration {

    @Bean(name = "myDog")
    public Animal getDog(){
        return new Dog();
    }
}
