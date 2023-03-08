package org.example;

import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    public void testA(){
        String s = new String(new char[]{'a','b'},0,2);
    }
    @Test
    public void testB(){
        String s2 = new String("java1");
        String s1 = "java1";
        System.out.println(s1.intern() == s1);
        System.out.println(s2.intern() == s2);
        System.out.println(s2.intern() == s1);
        System.out.println(s1.intern() == s2.intern());
    }
    @Test
    public void testC(){
        String s1 = new String("java1");
        String s2 = new StringBuffer().append("ja").append("va1").toString();
        System.out.println(s1.intern() == s1);
        System.out.println(s2.intern() == s2);
        String s3 = "java1";
        System.out.println(s1 == s2);
        System.out.println(s1.intern() == s3);
    }
    @Test
    public void testD(){
        String s1 = new String("java2");
        String s2 = new StringBuffer().append("ja").append("va1").toString();
        String s3 = "java3";
        System.out.println(s1.intern() == s1);
        System.out.println(s2.intern() == s2);
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s2.intern() == s3);
    }
}
