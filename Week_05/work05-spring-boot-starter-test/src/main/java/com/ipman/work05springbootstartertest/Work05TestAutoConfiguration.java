package com.ipman.work05springbootstartertest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ipipman on 2020/11/19.
 *
 * @version V1.0
 * @Package com.ipman.work05springbootstartertest
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/19 6:54 下午
 */

@Configuration
@ConditionalOnWebApplication //如果是Web就生效
@EnableConfigurationProperties(StudentProperties.class)
public class Work05TestAutoConfiguration {

    @Autowired
    StudentProperties studentProperties;

    @Bean
    public StudentService studentService(){
        StudentService studentService = new StudentService();
        studentService.setStudentProperties(studentProperties);
        return studentService;
    }
}
