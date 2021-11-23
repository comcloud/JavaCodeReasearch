/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.bean;

import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Test9.java v1.0 2021/9/26 9:18 下午 zhangyulei Exp $
 */
public class Test9 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n-- > 0) {
            int num = scan.nextInt();
            //因子差num
            int result = 6;
            while (true) {
                if (isNonValid(result, num)) {
                    System.out.println(result);
                    break;
                } else {
                    result++;
                }
            }
        }
    }

    public static boolean isNonValid(int count, int num) {
        int pred = 0;
        int sum = 0;
        for (int i = 1; i <= count; i++) {
            if (count % i == 0) {
                if (pred == 0 || i - pred >= num) {
                    pred = i;
                    sum++;
                }
            }
        }
        return sum >= 4;
    }
}
