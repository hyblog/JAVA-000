package com.springboot.frame;

import com.springboot.frame.proxy.saop.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by ipipman on 2020/11/17.
 *
 * @version V1.0
 * @Package com.springboot.frame
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/17 6:40 下午
 */
@SpringBootTest(classes = FrameCodeApplication.class)
//@ComponentScan(basePackages = {"com.springboot.frame"})
public class AspectJTests {

    @Autowired
    PersonServiceImpl personService;


    // AspectJ测试
    @Test
    public void doingTest(){
        personService.doing();
    }
}
