/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.MainTest.thread;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :ThreadVisit.java v1.0 2021/10/13 10:26 下午 zhangyulei Exp $
 */
public class ThreadVisit {

    private static int count = 0;

    public static void main(String[] args) {
        MyThread mt1 = new MyThread();
        MyThread mt2 = new MyThread();
        MyThread2 mt3 = new MyThread2();
        MyThread2 mt4 = new MyThread2();
        mt1.start();
        mt2.start();
        mt3.start();
        mt4.start();
    }

    //减操作
    static class MyThread extends Thread {

        public void run() {
            count--;
            System.out.println("减操作-->" + count);
        }
    }

    //加操作
    static class MyThread2 extends Thread {

        public void run() {
            count++;
            System.out.println("加操作-->" + count);
        }
    }
}


