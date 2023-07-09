package org.example.dispatch;

public class Father extends People {
    @Override
    public void sayHello(Animal animal) {
        animal.getName();
        System.out.println("sayHello");
    }
}
