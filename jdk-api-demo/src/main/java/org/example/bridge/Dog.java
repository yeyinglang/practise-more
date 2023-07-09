package org.example.bridge;


public class Dog extends Animal<Location>{
    @Override
    public void getName(Location s) {
//        s.getName();
        System.out.println("sub method " + s);
    }


//    @Override
//    public void getName(SmallLocation s) {
////        s.getName();
//        System.out.println("sub method " + s);
//    }
}
