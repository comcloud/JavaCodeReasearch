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
 * @version :Test4.java v1.0 2021/9/25 3:43 下午 zhangyulei Exp $
 */
public class Test4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        //操作次数
        int sum = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            //只有下一位不是数组最后一位时候
            if (i + 1 < arr.length - 2) {
                while (arr[i] <= arr[i + 1]) {
                    arr[i + 1]--;
                    sum++;
                }
            } else {
                //因为不用去关心两端，所以如果最后一个大也可以
                while (arr[i] <= arr[i + 1]) {
                    if(arr[arr.length -1 ] > arr[i + 1]){
                        break;
                    }
                    arr[i + 1]--;
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }
}
