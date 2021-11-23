package com.cloud.sort;

import java.util.Arrays;

/**
 * @version v1.0
 * @ClassName QuickSort
 * @Author rayss
 * @Datetime 2021/6/9 9:21 下午
 */

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = { 7, 4, 8, 3, 9, 10 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        //中间值
        int part = partition(arr, left, right);
        quickSort(arr, left, part - 1);
        quickSort(arr, part + 1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            //从右往左
            //这里如果不是>= 那么就有可能导致一直无法执行内部while，left永远都小于right
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }
}
