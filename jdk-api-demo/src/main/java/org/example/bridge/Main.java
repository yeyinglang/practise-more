package org.example.bridge;


public class Main {
    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.getName(new SmallLocation());
    }
}
