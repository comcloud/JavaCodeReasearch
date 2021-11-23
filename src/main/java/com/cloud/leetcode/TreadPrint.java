/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <p>
 *  多线程交替打印奇数偶数
 *  这里需要注意两个点
 *  可以有两种情况，currNum变量是否是volatile的
 *  - 是
 *      结果是无序，因为没有之间的调度
 *      不会打印100，因为都是需要小于100的
 *  - 不是，使用synchronized
 *      结果有序，因为获取锁之后打印成功后必须由下个内容打印才可以继续执行
 *      会打印100，因为奇数++之后会去偶数位置，那时候会打印100后才结束
 * </p>
 * @author zhangyulei
 * @version :TreadPrint.java v1.0 2021/9/24 9:27 上午 zhangyulei Exp $
 */
public class TreadPrint {

    private static  int currNum = 1;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
           while(currNum < 100){
               synchronized (TreadPrint.class){
                   if(currNum % 2 == 0){
                       System.out.println("偶数：" + currNum++);
                   }
               }
           }
        });
        //1
        Thread thread2 = new Thread(() -> {
            while(currNum < 100){
                synchronized (TreadPrint.class){
                    if(currNum % 2 != 0){
                        System.out.println("奇数：" + currNum++);
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
