package org.example.dispatch;

public class Main {
    public static void main(String[] args) {
        Main ma =new Main();
        Animal an = new Animal();
        Animal cat = new Cat();
        Animal dog = new Dog();
        ma.sayHell(an);
        ma.sayHell(dog);
    }
    public void sayHell(Animal animal) {
        animal.getName();
    }
    public void sayHell(Cat animal) {
        animal.getName();
    }
    public void sayHell(Dog animal) {
        animal.getName();
    }
}

