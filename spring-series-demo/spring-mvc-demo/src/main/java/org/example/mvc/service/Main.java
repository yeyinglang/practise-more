package org.example.mvc.service;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.TimeUnit;

/*
问题： 字符串常量池保存的是堆里面对象的引用？还是字面量

 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        StudentDemo demo = new StudentDemo("aa");

        StudentDemo demo1 = new StudentDemo("bb");
        StudentDemo demo2 = new StudentDemo("cc");
        Thread.currentThread().setContextClassLoader(null);
        TimeUnit.HOURS.sleep(1);
//        String s1 = new StringBuffer().append("ja").append("va2").toString();
//
//        String intern = s1.intern();
//        String s2 = "java2";
//        System.out.println(s1 == s2);
//        System.out.println( intern == s2);
//        String s2 = new StringBuffer().append("ja").append("va1").toString();
//        System.out.println(s1.intern() == s1);
//        System.out.println(s2.intern() == s2);
//        System.out.println(s1 == s2);
//        System.out.println(s1.intern() == s3);
    }
}