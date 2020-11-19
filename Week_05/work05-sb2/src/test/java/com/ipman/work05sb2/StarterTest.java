package com.ipman.work05sb2;

import com.ipman.work05springbootstartertest.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Created by ipipman on 2020/11/19.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/19 7:21 下午
 */
@SpringBootTest
public class StarterTest {

    @Resource
    StudentService studentService;

    @Test
    public void doing(){
        studentService.sayName();
    }
}
