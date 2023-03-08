package org.example.tomcat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello(){
        List<String> aa= new ArrayList<>();
        IntStream.range(0,100).parallel().forEach(x->{
            System.out.println(x+", classloader = "+Thread.currentThread().getContextClassLoader());
        });
        System.out.println("hello");
        return "hello";
    }

    public static void main(String[] args) {
        ClassLoader classLoader = Class.class.getClassLoader();
        ClassLoader classLoader1 = DemoController.class.getClassLoader();
        System.out.println(classLoader1);
        System.out.println(classLoader);
    }

}
