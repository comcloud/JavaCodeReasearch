package com.cloud.leetcode;

import java.util.Arrays;

/**
 * 合并两个有序数组
 *
 * @version v1.0
 * @ClassName MergeSortedArray
 * @Author rayss
 * @Datetime 2021/6/22 3:01 下午
 */

public class MergeSortedArray {
    public static void main(String[] args) {
        int[] num1 = {5, 6, 7, 8, 9, 10, 0, 0, 0, 0};
        int[] num2 = {2, 3, 4, 5};
        merge(num1, 6, num2, 4);
        System.out.println(Arrays.toString(num1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //p表示nums1总长度索引
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            //每次选出来最大的值在nums1数组倒序存放
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        //此时说明num1数组中的内容都存放完毕，但是要将num2数组在前面顺序存放
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }
}
