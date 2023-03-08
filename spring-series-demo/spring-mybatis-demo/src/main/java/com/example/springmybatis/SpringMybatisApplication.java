package com.example.springmybatis;

import com.example.springmybatis.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringMybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringMybatisApplication.class, args);
        StudentService bean = context.getBean(StudentService.class);
//        bean.insertStudent();
        System.out.println("hello");
    }

}
