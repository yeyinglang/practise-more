package org.example.map;

import org.example.util.Utils;

import java.util.concurrent.RecursiveTask;

public class ForkTask extends RecursiveTask<Integer> {
    private int[] arr ;
    private int left;
    private int right;

    public ForkTask(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    @Override
    protected Integer compute() {
        if(right-left<=5) {
            int result =0;
            for (int i = left; i < right; i++) {
                result += arr[i];
            }
            Utils.toMsg( "终点：left = "+left + ", right="+ right+", result = " + result);
            return result;
        }
        Utils.sleepRandomMills();
        int mid = (left+right)/2;
        ForkTask leftTask = new ForkTask(arr, left, mid);
        ForkTask rightTask = new ForkTask(arr, mid, right);
        leftTask.fork();
        rightTask.fork();
        int result = leftTask.join()+rightTask.join();
        Utils.toMsg( "left = "+left + ", right="+ right+", result = " + result);
        return result;
    }
}
