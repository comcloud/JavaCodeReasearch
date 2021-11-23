package com.cloud.leetcode;

/**
 * @version v1.0
 * @ClassName Sqrt
 * @Author rayss
 * @Datetime 2021/6/25 12:07 下午
 */

public class Sqrt {

    public static void main(String[] args) {
        int x = 2147395599;
        System.out.println(mySqrt(x));
    }

    public static int mySqrt(int x) {
        for (int i = 1; ; i++) {
            if (i * i > x) {
                return i - 1;
            }
        }
    }
}
