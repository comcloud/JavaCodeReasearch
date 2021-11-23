package com.cloud.MainTest.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 *
 * @version v1.0
 * @ClassName ProAndCon_1
 * @Author rayss
 * @Datetime 2021/4/22 11:33 下午
 */

public class ProAndCon_1 {
    private static final Object LOCK = new Object();
    private static final int FULL = 10;
    private static  int count = 0;

    public static void main(String[] args) {
        new Thread(new ProAndCon_1.Product(),"pro-1 : ").start();
        new Thread(new ProAndCon_1.Product(),"pro-3 : ").start();
        new Thread(new ProAndCon_1.Consumer(),"con-1 : ").start();
        new Thread(new ProAndCon_1.Product(),"pro-2 : ").start();
        new Thread(new ProAndCon_1.Consumer(),"con-2 : ").start();
    }

    private static class Consumer implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < FULL; i++) {
                TimeUnit.SECONDS.sleep(1L);
                synchronized (LOCK) {
                    while (count <= 0) {
                        LOCK.wait();
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    LOCK.notifyAll();
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
                synchronized (LOCK) {
                    while (count == FULL) {
                        LOCK.wait();
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }
}


