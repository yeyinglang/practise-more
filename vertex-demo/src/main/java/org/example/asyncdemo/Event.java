package org.example.asyncdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class Event {
    private Thread currentThread;
    int sum;

    public List<String> results = new ArrayList<>();
    private int threshold = 10;

    public Event() {
    }
    public Event(Thread currentThread) {
        this.currentThread = currentThread;
    }

    public synchronized void finishPart(int part) {
        sum += part;
        unpark();
    }

    public boolean succcess() {
        return sum == threshold;
    }

    public String get() {
        while(!succcess()) {
            LockSupport.park();
        }
        return results.toString();
    }

    public synchronized void setResult(String res) {
        this.results.add(res);
    }

    public void unpark(){
        LockSupport.unpark(currentThread);
    }

}
