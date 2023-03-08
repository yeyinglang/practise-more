package org.example.utils;

public class PrintUtils {
    public static void printMsg(String... msgs) {
        for (String msg : msgs
        ) {
            System.out.print(msg + ",");
        }
        System.out.println();
    }
}
