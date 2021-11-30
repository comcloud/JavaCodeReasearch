/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.test1;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Test.java v1.0 2021/11/11 4:05 下午 zhangyulei Exp $
 */
public class Test {

    volatile int a = 1;
    volatile int b = 1;

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(test::add).start();
        new Thread(test::compare).start();

    }

    public void add() {
        System.out.println("add start");
        for (int i = 0; i < 10000; i++) {
            a++;
            b++;
        }
        System.out.println("add done");
    }

    public void compare() {
        System.out.println("compare start");
        for (int i = 0; i < 10000; i++) {
            //a始终等于b吗？
            if (a < b) {
                System.out.println("a:" + a + ",b:" + b + " " + (a > b));
                //最后的a>b应该始终是false吗？
            }
        }
        System.out.println("compare done");
    }
}
