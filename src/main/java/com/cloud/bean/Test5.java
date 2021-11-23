/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.bean;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Test5.java v1.0 2021/9/26 8:06 下午 zhangyulei Exp $
 */
public class Test5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            //存储每个位置的结果
            int[] result = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }
            for (int i = 0; i < arr.length; i++) {
                //记录分数
                int sum = arr[i];
                for (int j = i + arr[i]; j < arr.length; j += arr[j]) {
                    sum += arr[j];
                }
                result[i] = sum;
            }
            System.out.println(Arrays.stream(result).max().getAsInt());
        }

    }
}
