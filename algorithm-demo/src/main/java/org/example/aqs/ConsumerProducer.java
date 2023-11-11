package org.example.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducer {
    public static void main(String[] args) {
        Repo repo = new Repo(5);
        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer(repo)).start();
            new Thread(new Producer(repo)).start();
        }
//        LockSupport.parkNanos();

    }

    public static class Repo {
        private final int max;
        volatile int num;
        ReentrantLock lock = new ReentrantLock();
        Condition notEmpty = lock.newCondition();
        Condition isEmpty = lock.newCondition();

        public Repo(int max) {
            this.max = max;
        }

        public void consume() {
            try {
                lock.lock();
                boolean flag = false;
                if (num <= 0) {
                    System.out.println("消费不足，仓库空了");
                    flag = true;
                    isEmpty.await();
                }
                num--;
                if(flag) {
                    System.out.println("被唤醒的，消费1个，仓库还剩下" + num);
                }else {
                    System.out.println("消费1个，仓库还剩下" + num);
                }
                notEmpty.signal();
                notEmpty.signalAll();
                lock.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

        public void produce() {
            try {
                lock.lock();
                if (num >max) {
                    System.out.println("仓库满了，等待消费者消费");
                    notEmpty.await();
                }
                num++;
                System.out.println("生产1个，仓库还剩下" + num);
                isEmpty.signalAll();
                lock.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class Producer implements Runnable {
        private  Repo repo;
        Producer( Repo repo) {
            this.repo = repo;
        }

        public void run() {
            repo.produce();
        }
    }

    public static class Consumer implements Runnable {
        private  Repo repo;
        Consumer( Repo repo) {
            this.repo = repo;
        }

        public void run() {
            repo.consume();
        }
    }
}
