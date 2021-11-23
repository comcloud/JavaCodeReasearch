/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Scanner;

/**
 * <p>
 * 4 1
 * 3 2 2 1
 * </p>
 * @author zhangyulei
 * @version :Test1.java v1.0 2021/9/22 7:30 下午 zhangyulei Exp $
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        int sum = 0;
        for (int i = k - 1; i < arr.length - 1; i= (i + 1) % arr.length - 1) {
            if(arr[i + 1] != 0){
                sum++;
                if(arr[i] > 1){
                    arr[i]--;
                }else{
                    arr[i + 1]--;
                }
            }else {
                break;
            }
        }
        System.out.println(sum);
    }
}
