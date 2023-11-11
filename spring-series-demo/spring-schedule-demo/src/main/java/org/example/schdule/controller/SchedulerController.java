package org.example.schdule.controller;

import org.example.schdule.schduler.MySchedulerConfigurer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SchedulerController {

    @Resource
    MySchedulerConfigurer mySchedulerConfigurer;

    @GetMapping(path = "/scheduler")
    public String schedulerChanged(@RequestParam(name = "hour") int hour, @RequestParam(name="minutes") int minutes) {
        mySchedulerConfigurer.setChanged(true);
        mySchedulerConfigurer.setHour(hour);
        mySchedulerConfigurer.setMinutes(minutes);
        System.out.println("init success");
        return "time = "+ hour + ""  + minutes;
    }

}
