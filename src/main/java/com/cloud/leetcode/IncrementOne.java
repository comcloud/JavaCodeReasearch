package com.cloud.leetcode;

import java.util.Arrays;

/**
 * @version v1.0
 * @ClassName IncreOne
 * @Author rayss
 * @Datetime 2021/6/24 3:04 下午
 */

public class IncrementOne {

    public static void main(String[] args) throws InterruptedException {
        int[] digits = {9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        int[] plusOne = plusOne(digits);
        System.out.println(Arrays.toString(plusOne));
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        //跳出for循环，说明数字全部是9
        int[] temp = new int[digits.length + 1];
        temp[0] = 1;
        return temp;
    }
}
