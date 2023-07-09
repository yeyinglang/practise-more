package org.example.bridge;


public class Animal<T extends Location> {
    public void getName(T t){
        System.out.println("ttt");
    }

}
