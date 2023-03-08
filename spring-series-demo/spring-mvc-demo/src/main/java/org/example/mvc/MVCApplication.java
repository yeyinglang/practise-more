package org.example.mvc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * demo test
 */
@SpringBootApplication
public class MVCApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MVCApplication.class);
//        run.getBean()
    }
}
