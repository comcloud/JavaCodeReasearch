package com.cloud.tree;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @version v1.0
 * @ClassName HeapSort
 * @Author rayss
 * @Datetime 2021/5/31 9:00 上午
 */

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        //i==arr.length/2 -1 表示非叶子节点数值
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustArray(arr, i, arr.length);
        }
        int temp;
        //不断的替换第一位和最后一位，因为每次调整之后最后一个值都是当前最大或者最小的
        for (int j = arr.length - 1; j >= 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustArray(arr, 0, j);
        }

    }

    private static void adjustArray(int[] arr, int i, int length) {
        int temp = arr[i];

        //k == i * 2 + 1表示给k赋值为索引为i的节点的左节点位置索引
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //选出左右子树最大的节点值
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果此时左右节点中有比较大的就放到父节点上
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //将temp存放到调整后的i位置
        arr[i] = temp;
    }
}
