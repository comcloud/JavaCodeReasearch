package com.cloud.frequentlyused;

import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @version v1.0
 * @ClassName MergeSort
 * @Author rayss
 * @Datetime 2021/8/9 3:02 下午
 */

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] arr = new int[800_0000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(800_0000);
        }
        long start = System.currentTimeMillis();
        sort(arr);
        System.out.println((System.currentTimeMillis() - start));
    }

    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        //递归终止条件，只有在left一直小于right时候才停止
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解，会向左一路拆解下去
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //分解完成之后，合并
            merge(arr, left, mid, right, temp);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //合并算法
        /*
         * 1.将左侧的数据迁移到temp中，按照大小比较
         * 2.因为可能存在某一个没有迁移完成，所以将剩下的两方做判断继续迁移
         * 3.将temp数组迁移到arr中
         * */
        //1
        int i = left, j = mid + 1, t = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        //2
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        //3，迁移位置：从left~right
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }

}
