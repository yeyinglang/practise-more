package org.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class AddressService2 implements IAddressService{
    public AddressService2(){
        System.out.println("init addresss 2");
    }
}
