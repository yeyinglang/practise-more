package org.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class AddressService implements IAddressService {
    public AddressService(){
        System.out.println("init address");
    }
}
