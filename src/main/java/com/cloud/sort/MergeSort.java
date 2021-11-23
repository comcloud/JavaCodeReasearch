/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.sort;

import java.util.Arrays;

/**
 * <p>
 * 归并排序
 * </p>
 * @author zhangyulei
 * @version :MergeSort.java v1.0 2021/9/18 2:16 下午 zhangyulei Exp $
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = { 4, 23, 4, 2, 34, 2, 3, 5, 42, 5 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid - 1, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        /*
         * 1.顺序存放
         * 2.将没有存放完成的放到temp
         * 3.temp迁移到arr
         * */
        int i = left, j = mid + 1, t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        //因为是索引，所以需要+1
        System.arraycopy(temp, 0, arr, left, right - left + 1);
    }
}
