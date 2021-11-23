/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Scanner;

/**
 * <p>
 *  牛牛排队问题
 * </p>
 * @author zhangyulei
 * @version :NiuNiuQueue.java v1.0 2021/9/20 3:48 下午 zhangyulei Exp $
 */
public class NiuNiuQueue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println(getResult(arr));
    }
    public static int getResult(int[] arr){
        for (int i = 0; ; i++) {
            if(arr[i] - i <= 0){
                return i+1;
            }
            if(i + 1 % arr.length == 0){
                i = -1;
            }
        }
    }
}
