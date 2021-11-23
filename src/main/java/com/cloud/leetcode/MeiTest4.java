/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :MeiTuanTest1.java v1.0 2021/10/10 10:08 上午 zhangyulei Exp $
 */
public class MeiTest4 {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        //景色值
        int[] value = new int[n];
        for (int i = 0; i < value.length; i++) {
            value[i] = scanner.nextInt();
        }
        //路径值，i -> arr[i] 路径指向
        int[] arr = new int[n - 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        //总共走的天数
        int sumDay = n * (n + 1) / 2;
        //每天的最大景色值
        int[] maxValue = new int[n];
        for (int i = 0; i < sumDay; i++) {
            //走多少
            int number = (i + 1) % n;
            //从第几个景点开始走
            int start = i % n ;
            //最大的景色值
            int max = value[start];
            for (int j = start; j < number; j = arr[j]) {
                //这里需要根据路径指向到对应的景点
                if (value[j] > max) {
                    max = value[j];
                }
            }
            maxValue[i % n] = max;
        }
        for (int j : maxValue) {
            System.out.println(j + "\t");
        }
        System.out.println();
    }
}
