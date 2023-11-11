package com.example.springimport.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class DemoDao {
    private int idx=0;
    public DemoDao() {
        System.out.println("init dao" + idx++);
    }

    @Scheduled
    public void say(){
        System.out.println("hello");
    }

}
