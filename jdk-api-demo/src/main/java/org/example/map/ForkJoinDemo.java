package org.example.map;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        int[] array = IntStream.range(0, 101).toArray();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new ForkTask(array, 0, 100));
        System.out.println(submit.get());
    }
}
