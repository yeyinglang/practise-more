package org.example.unsafe;

import org.example.map.ForkTask;
import org.example.util.Utils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ForkJoinTask;

public class StringUnsafeDemo {
    private static Unsafe unsafe;

    static {
        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }

    static class WorkQueue {
        int top;
        int base;
        ForkJoinTask[] forkJoinTasks;

        public WorkQueue() {
            int length = 8;
            forkJoinTasks = new ForkJoinTask[length];
            top = base = 1 << 13;
        }

    }


    public static void main(String[] args) throws NoSuchFieldException {
        Class<ForkJoinTask[]> aClass = ForkJoinTask[].class;
        int ABASE = unsafe.arrayBaseOffset(aClass);
        int scale = unsafe.arrayIndexScale(aClass);
        Utils.toMsg(ABASE);
        Utils.toMsg(scale);

//        前导0 ；
        int ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
        System.out.println(ASHIFT);

        int top = 1<<13;
        int length = 8;
        WorkQueue workQueue = new WorkQueue();
        long i = (((length - 1) & top) << ASHIFT) + ABASE;
        ForkJoinTask[] forkJoinTasks = workQueue.forkJoinTasks;
        ForkTask forkTask = new ForkTask(null,0,1);
        unsafe.putOrderedObject(forkJoinTasks, i, forkTask);
        top+=1;
        long j = (((length - 1) & top) << ASHIFT) + ABASE;
        unsafe.putOrderedObject(forkJoinTasks, j, forkTask);

        System.out.println("");

//


    }

}
