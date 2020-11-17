package com.springboot.frame.proxy.saop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ipipman on 2020/11/17.
 *
 * @version V1.0
 * @Package com.springboot.frame.proxy.aspectjs
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/17 7:01 下午
 */
@RestController
public class PersonController {

    @Autowired
    PersonServiceImpl personService;

    @GetMapping("/test")
    public void test(){
        personService.doing();
    }
}
