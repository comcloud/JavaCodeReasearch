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
 * @version :Main.java v1.0 2021/9/10 7:26 下午 zhangyulei Exp $
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int[] maxArray = getMaxArray(m, arr);
        System.out.println(Arrays.stream(maxArray).sum());
    }

    private static int[] getMaxArray(int number, int[] arr) {
        int[] maxArray = new int[number];
        for (int i = 0; i < number; i++) {
            maxArray[i] = getMax(arr);
        }
        return maxArray;
    }

    private static int getMax(int[] arr) {
        int maxValue = 0;
        int currentIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (maxValue < arr[i]) {
                maxValue = arr[i];
                arr[i] = 0;
                currentIndex = i;
            }
        }
        arr[currentIndex] = 0;
        return maxValue;
    }

}
