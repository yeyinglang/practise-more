package org.example.schdule.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoB {

    @Autowired
    private DemoC demoC;
}
