package com.example.springimport.envir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    public MyEnvironmentPostProcessor(){
        System.out.println("MyEnvironmentPostProcessor");
    }
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String hello = environment.getProperty("lk.name");
        System.out.println(hello + "///");
    }
}
