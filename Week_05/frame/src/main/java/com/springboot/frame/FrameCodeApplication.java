package com.springboot.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.springboot.frame"})
@SpringBootApplication
public class FrameCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrameCodeApplication.class, args);
	}

}
