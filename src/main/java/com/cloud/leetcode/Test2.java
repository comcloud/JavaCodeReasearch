/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Scanner;

/**
 * <p>
 *  折纸游戏
 *  4
 * 2 6 5 4
 * 1 5 7 6
 * 9 8 8 7
 * 1 4 7 8
 *
 * 101
 *  11
 *1000
 * </p>
 * 3 - 0 3 - 1
 * @author zhangyulei
 * @version :Test2.java v1.0 2021/9/22 7:52 下午 zhangyulei Exp $
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        int temp = n;
        //左右折叠
        while(temp != 1){
            //循环一次，折叠一次
            for (int i = 0; i < temp; i++) {
                for (int j = 0; j < temp / 2; j++) {
                    //固定行，计算对应的列索引
                    arr[i][j] = arr[i][n - 1 - j];
                }
            }
            temp /= 2;
        }
        //上下折叠
        temp = n;
        while(temp != 1){
            //循环一次，折叠一次
            for (int i = 0; i < temp / 2; i++) {
                for (int j = 0; j < temp; j++) {
                    //固定列，计算对应的行索引
                    arr[i][j] = arr[n - 1 - i][j];
                }
            }
            temp /= 2;
        }
        System.out.println(arr[0][0]);
    }
}
