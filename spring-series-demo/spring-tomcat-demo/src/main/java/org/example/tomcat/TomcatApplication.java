package org.example.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 使用外部的tomcat 启动，调用configure方法，完成spring的初始化
 */
@EnableScheduling
@SpringBootApplication
public class TomcatApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TomcatApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TomcatApplication.class);
    }
}
