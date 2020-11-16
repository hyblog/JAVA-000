package com.springboot.frame.ioc.xml;

import java.util.List;

/**
 * Created by ipipman on 2020/11/16.
 *
 * @version V1.0
 * @Package com.springboot.frame.ioc
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/16 11:02 下午
 */
public class Student {

    private String name;

    private Integer age;

    private List<String> classList;

    public Student(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getClassList() {
        return classList;
    }

    public void setClassList(List<String> classList) {
        this.classList = classList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", classList=" + String.join(",", classList) +
                '}';
    }
}
