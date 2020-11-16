package com.springboot.frame.ioc.xml;


/**
 * Created by ipipman on 2020/11/16.
 *
 * @version V1.0
 * @Package com.springboot.frame.ioc
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/16 11:02 下午
 */
public class HelloService {

    private Student student;

    private Animal animal;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String hello() {
        return animal.getName();
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

}