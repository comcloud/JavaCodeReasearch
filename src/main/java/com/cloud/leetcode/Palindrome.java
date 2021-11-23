package com.cloud.leetcode;

/**
 * 判断是否是回文数
 *
 * @version v1.0
 * @ClassName Palindrome
 * @Author rayss
 * @Datetime 2021/6/24 1:10 下午
 */

public class Palindrome {
    public static void main(String[] args) {
        int x = 10;
        System.out.println(isPalindrome(x));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int count = 0, y = x;
        while (y != 0) {
            count = count * 10 + y % 10;
            y /= 10;
        }
        return count == x;
    }
}
