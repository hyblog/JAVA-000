package com.springboot.frame.ioc.ann;

import com.springboot.frame.ioc.xml.Animal;
import com.springboot.frame.ioc.xml.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by ipipman on 2020/11/16.
 *
 * @version V1.0
 * @Package com.springboot.frame.ioc.ann
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/16 11:16 下午
 */
@Component
public class Hello1Service {

    private Student student;

    @Qualifier(value = "myMonkey") //多个相同的Bean名称用Qualifier注定
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
