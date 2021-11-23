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
 * @version :JdTest1.java v1.0 2021/10/9 7:39 下午 zhangyulei Exp $
 */
public class JdTest2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int x = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);

        //最小数量
        int minSum = 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            double mid = arr[i + 1] - arr[i];
            //如果上一个与当前的差值不超过x则满足要求，或者如果二者之差/2也不超过 x需要借用一次k的机会，但是他需要大于0
            boolean non = !(mid <= x || mid / 2.0 <= x * 1.0 && k-- > 0);
            if (non) {
                minSum++;
            }
        }
        System.out.println(minSum);
    }

}