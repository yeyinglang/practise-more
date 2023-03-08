package org.example.utils;

public class PrintUtils {
    public static void printMsg(Object... msgs) {
        for (Object msg : msgs
        ) {
            System.out.print(msg + " >> " );
        }
        System.out.println();
    }
}
