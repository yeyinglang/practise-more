package org.example.map;

import org.example.util.Utils;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDemo {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Utils.toMsg(random.nextInt());
        }
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        localRandom.nextInt();
        ConcurrentHashMap co = new ConcurrentHashMap();
//        co.put()
        int n = 64;
        int RESIZE_STAMP_BITS = 16;
        int num = Integer.numberOfLeadingZeros(n) | (1 << (RESIZE_STAMP_BITS - 1));
        System.out.println(Integer.numberOfLeadingZeros(64));
        System.out.println(Integer.toBinaryString(num));
    }
}
