package com.ipman.work05springbootstartertest;

/**
 * Created by ipipman on 2020/11/19.
 *
 * @version V1.0
 * @Package com.ipman.work05springbootstartertest
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/19 7:01 下午
 */
public class StudentService {

    StudentProperties studentProperties;

    public void setStudentProperties(StudentProperties studentProperties) {
        this.studentProperties = studentProperties;
    }

    public String sayName(){
        return studentProperties.getName();
    }

}
