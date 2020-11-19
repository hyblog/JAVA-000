package com.ipman.work05sb2.api;

import com.ipman.work05springbootstartertest.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by ipipman on 2020/11/19.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.api
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/19 7:13 下午
 */
// 必须是Web环境才能引入bean
@RestController
public class StarterController {

    // 获取自动装配starter的bean，
    @Resource
    StudentService studentService;

    @GetMapping(value = "/starter")
    public Object testStarter(){
        return studentService.sayName();
    }
}
