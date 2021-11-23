/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 * 逆序对个数
 * </p>
 * @author zhangyulei
 * @version :ReversePairs.java v1.0 2021/9/24 11:52 上午 zhangyulei Exp $
 */
public class ReversePairs {

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 3, 8, 6, 1 };
        int inversionCount = mergeSort(arr, 0, arr.length);
        printArray(arr);
        System.out.println("inversionCount: " + inversionCount);
    }

    public static int mergeSort(int[] arr, int start, int end) {
        int inversionCount = 0;
        int length = end - start;
        if (length > 1) { // 长度大于1才需要排序
            int mid = (start + end) / 2;
            inversionCount += mergeSort(arr, start, mid); // sort left
            inversionCount += mergeSort(arr, mid, end); // sort right
            inversionCount += merge(arr, start, mid, end);
        }
        return inversionCount;
    }

    public static int merge(int[] arr, int start, int mid, int end) {
        // check input
        if (arr == null || start < 0 || end > arr.length) {
            return 0;
        }
        int[] temp = new int[end - start];
        int inversionCount = 0;
        int i = start; // 左半部分索引
        int j = mid; // 右半部分索引
        int k = 0; // temp数组索引
        while (i < mid && j < end) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                // 一旦 arr[i] > arr[j]，就会有 (mid - i) 个逆序对产生
                inversionCount += mid - i;
            }
        }
        if (i != mid) {
            System.arraycopy(arr, i, temp, k, mid - i);
        }
        if (j != end){
            System.arraycopy(arr, j, temp, k, end - j);
        }
        System.arraycopy(temp, 0, arr, start, temp.length);
        return inversionCount;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
