package org.example.util;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static void toMsg(Object msg) {
        LocalDateTime now = LocalDateTime.now();

        System.out.println("curTime = " + now + ", curThread = " +
                Thread.currentThread().getName() + "--- msg = " + msg);
    }

    public static void sleepRandomMills(){
        double random = Math.random() * 1000;
        try {
            TimeUnit.MILLISECONDS.sleep((long) random);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
