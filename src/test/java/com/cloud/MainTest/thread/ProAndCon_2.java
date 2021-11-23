package com.cloud.MainTest.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version v1.0
 * @ClassName ProAndCon_2
 * @Author rayss
 * @Datetime 2021/4/22 11:57 下午
 */

public class ProAndCon_2 {
    private static final Lock LOCK = new ReentrantLock();

    private static final Condition NOT_FULL = LOCK.newCondition();
    private static final Condition NOT_EMPTY = LOCK.newCondition();

    private static final int FULL = 10;
    private static int count = 0;

    public static void main(String[] args) {
        new Thread(new ProAndCon_2.Product(),"pro-1 : ").start();
        new Thread(new ProAndCon_2.Consumer(),"con-1 : ").start();
        new Thread(new ProAndCon_2.Product(),"pro-2 : ").start();
        new Thread(new ProAndCon_2.Consumer(),"con-2 : ").start();
        new Thread(new ProAndCon_2.Product(),"pro-3 : ").start();
        new Thread(new ProAndCon_2.Consumer(),"con-3 : ").start();
    }

    private static class Consumer implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < FULL; i++) {
                TimeUnit.SECONDS.sleep(1L);
                try {
                    LOCK.lock();
                    while (count <= 0) {
                        NOT_EMPTY.await();
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    NOT_FULL.signal();
                } finally {
                    LOCK.unlock();
                }

            }
        }
    }

    private static class Product implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < FULL; i++) {
                TimeUnit.SECONDS.sleep(1L);
                try {
                    LOCK.lock();
                    while (count == FULL) {
                        NOT_FULL.await();
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    NOT_EMPTY.signal();
                }finally {
                    LOCK.unlock();
                }
            }
        }
    }
}
