package org.example.tomcat.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Scheduled(cron="*/5 * * * * *")
    public void say(){
        Thread.currentThread().getContextClassLoader();
        System.out.println("hello hello");
    }

}
