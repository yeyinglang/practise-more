package org.example.demo.bean;

public class AddressDemo extends StudentDemo {
    public static void main(String[] args) {


    }

    public static void testDynamicClass() {
        for (int i = 0; i < 10; i++) {

        }
        byte[]a = new byte[300*1024];
        invoke();
        System.out.println("1111");
        byte[]a1 = new byte[100*1024];
        byte[]a2 = new byte[100*1024];
    }

    private static void invoke() {
        byte[][] arr = new byte[10][];
        for (int i = 0; i < 8; i++) {
            arr[i]=new byte[200*1024];
        }

    }
}
