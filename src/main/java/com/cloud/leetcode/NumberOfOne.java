/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Arrays;

/**
 * <p>
 *  输出n以内转换为二进制1的个数
 * </p>
 * @author zhangyulei
 * @version :NumberOfOne.java v1.0 2021/10/11 4:04 下午 zhangyulei Exp $
 */
public class NumberOfOne {

    public static void main(String[] args) {
        int n = 2;
        System.out.println(Arrays.toString(new NumberOfOne().countBits(n)));
    }

    public int[] countBits(int n) {
        int[] arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i] = getCountOfZero(i);
        }
        return arr;
    }

    public String toBinaryString(int n) {
        StringBuilder builder = new StringBuilder();
        while (n != 0) {
            builder.append(n % 2);
            n /= 2;
        }
        return builder.reverse().toString();
    }

    public int getCountOfZero(int n) {
        String binaryString = toBinaryString(n);
        int count = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

}
