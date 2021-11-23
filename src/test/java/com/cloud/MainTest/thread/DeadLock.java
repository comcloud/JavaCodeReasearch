package com.cloud.MainTest.thread;

import java.util.concurrent.TimeUnit;

/**
 * 死锁示例
 *
 * @version v1.0
 * @ClassName DeadLock
 * @Author rayss
 * @Datetime 2021/6/8 12:57 下午
 */

public class DeadLock {

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (DeadLock.class){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Object.class){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (Object.class){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (DeadLock.class){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }).start();
    }
}
