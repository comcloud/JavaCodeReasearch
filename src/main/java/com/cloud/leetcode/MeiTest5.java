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
public class MeiTest5 {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] value = new int[n];
        int[] p = new int[n - 1];

        for (int i = 0; i < value.length; i++) {
            value[i] = scanner.nextInt();
        }
        for (int i = 0; i < p.length; i++) {
            p[i] = scanner.nextInt();
        }

        int result = 0;
        int sum = Arrays.stream(value).sum();
        for (int i = 0; i < n; i++) {
            result += (n * value[i] - sum) * (n * value[i] - sum);
        }
        System.out.println(result % 1_000_000_007);
    }
}
