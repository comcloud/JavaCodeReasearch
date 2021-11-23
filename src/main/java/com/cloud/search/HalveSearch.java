/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.search;

/**
 * <p>
 *  普通的二分查找
 * </p>
 * @author zhangyulei
 * @version :HalveSearch.java v1.0 2021/9/27 7:52 下午 zhangyulei Exp $
 */
public class HalveSearch {

    public static void main(String[] args) {
        int[] arr = { 3, 3, 4, 4, 4, 5, 6, 6, 6, 7, 8, 8, 12, 13, 15, 16, 21, 21, 22, 24, 24, 27, 28, 32, 34, 35, 35,
                      36, 36, 39, 40, 41, 41, 42, 44, 44, 45, 45, 47, 47, 47, 47, 48, 48, 50, 51, 51, 53, 53, 53, 54,
                      54, 54, 56, 56, 57, 59, 60, 60, 60, 60, 61, 62, 63, 65, 65, 65, 65, 67, 67, 68, 70, 71, 71, 74,
                      75, 75, 79, 81, 84, 84, 86, 86, 87, 90, 90, 90, 90, 91, 92, 93, 94, 94, 94, 95, 97, 97, 98, 98,
                      99 };
        int value = 97;
        System.out.println(halveSearch(arr, value));
        System.out.println(halveByIterate(arr, value));
        System.out.println(upper_bound_(arr.length, value, arr));
    }

    public static int halveSearch(int[] arr, int value) {
        return halveByRecursion(arr, 0, arr.length - 1, value) + 1;
    }

    private static int halveByRecursion(int[] arr, int left, int right, int value) {
        if (left > right) {
            return arr.length;
        }
        int mid = (left + right) / 2;
        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] > value) {
            //往左
            return halveByRecursion(arr, 0, mid, value);
        } else {
            return halveByRecursion(arr, mid + 1, right, value);
        }

    }

    private static int halveByIterate(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == value) {
                return mid + 1;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return arr.length + 1;
    }

    public static int upper_bound_(int n, int v, int[] a) {
        // write code here
        if (v > a[n - 1]) {
            return n + 1;
        }
        int start = 0;
        int end = n - 1;
        int mid = start + (end - start) / 2;
        while (start < end) {
            if (a[mid] >= v) {
                end = mid;
            } else {
                start = mid + 1;
            }
            mid = start + (end - start) / 2;
        }
        return mid + 1;
    }
}
