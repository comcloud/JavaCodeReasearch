/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Scanner;

/**
 * <p>
 *  计算给定一个数字，计算出他需要多少个2的幂次方之和可以得出结果
 * </p>
 * @author zhangyulei
 * @version :MathTest.java v1.0 2021/9/20 4:02 下午 zhangyulei Exp $
 */
public class MathTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getResult(n));
    }
    public static int getResult(int n){
        int sum = 1;
        while(n != 1){
            if(n % 2 != 0){
                sum++;
                n -= 1;
            }
            n /= 2;
        }
        return sum;
    }

}
