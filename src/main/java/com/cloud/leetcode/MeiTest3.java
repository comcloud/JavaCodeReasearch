/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :MeiTuanTest1.java v1.0 2021/10/10 10:08 上午 zhangyulei Exp $
 */
public class MeiTest3 {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        //每个人最少多少礼物
        int[] a = new int[n];
        //商店的物品价格
        int[] b = new int[m];
        //每个礼物不得低于单价
        int[] c = new int[n];
        int[] result = new int[n];

        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = scanner.nextInt();
        }
        for (int i = 0; i < c.length; i++) {
            c[i] = scanner.nextInt();
        }
        Arrays.sort(b);
        for (int i = 0; i < a.length; i++) {
            //商店索引
            int temp = 0;
            //循环到符合条件的位置
            for (; temp < b.length; temp++) {
                if (b[temp] >= c[i]) {
                    break;
                }
            }
            //如果此时剩下的数量不足则说明礼物不够,都各自-1是因为数组索引从0开始
            if (b.length - 1 - temp < a[i] - 1) {
                result[i] = -1;
                continue;
            }
            //需要的礼物数量
            for (int j = 0; j < a[i]; j++) {
                result[i] += b[temp++];
            }
        }
        for (int j : result) {
            System.out.print(j + "\t");
        }
        System.out.println();

    }
}
