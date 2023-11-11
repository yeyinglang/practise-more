package org.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StudentService {

    /**
     * 按照类型优先匹配；
     * AutowiredAnnotationBeanPostProcessor
     * postProcessPropertyValues -
     *
     *
     */
    @Resource
    @Qualifier("addressService2")
    public IAddressService addressService23;

    public StudentService() {
        System.out.println("init student");
    }

    public void say(){
//        System.out.println(addressService23.size());
    }
}
