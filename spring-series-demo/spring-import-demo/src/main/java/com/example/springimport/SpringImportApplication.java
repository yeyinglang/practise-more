package com.example.springimport;

import com.example.springimport.bean.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.springimport.dao.DemoDao;

/**
 * 三种方式实现配置类的导入；
 *
 */
@SpringBootApplication
public class SpringImportApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringImportApplication.class, args);
        DemoDao bean = run.getBean(DemoDao.class);
        bean.say();
        DemoDao bean1 = run.getBean(DemoDao.class);
        run.stop();
    }

}
