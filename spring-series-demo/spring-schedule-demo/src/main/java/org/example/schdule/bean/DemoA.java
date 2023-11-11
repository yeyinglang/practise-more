package org.example.schdule.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DemoA {

    @Autowired
    private DemoB demoB;


    @Transactional
    public String sayHello() {
        System.out.println("aa");
        return "aa";
    }

}
