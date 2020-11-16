package com.springboot.frame;

import com.springboot.frame.ioc.xml.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by ipipman on 2020/11/16.
 *
 * @version V1.0
 * @Package com.springboot.frame
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/16 11:08 下午
 */
@SpringBootTest
@ContextConfiguration(locations = "classpath:ioc/hello.xml") //指定SpringBoot上下文加载自定义XML Bean
public class XmlIocAppTests {

    @Autowired
    HelloService helloService;

    @Test
    public void testXmlBean() {
        System.out.println(helloService.hello());
        System.out.println(helloService.getAnimal().getName());
        System.out.println(helloService.getStudent());
        //Dog
        //Dog
        //Student{name='zhangsan', age=13, classList=math,english}
    }

}


