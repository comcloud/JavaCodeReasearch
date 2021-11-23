/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.sort;

import java.util.Arrays;

/**
 * <p>
 * 堆排序，他的过程就是不断转换为大顶堆或者小顶堆然后找到最大或者最小的那个值，思想参考选择排序
 * </p>
 * @author zhangyulei
 * @version :HeapSort.java v1.0 2021/10/10 9:07 上午 zhangyulei Exp $
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = { 4, 6, 8, 10, 3, 5, 12 };
        headSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void headSort(int[] arr) {
        //先一次调整为大顶堆
        //arr.length = / 2 - 1表示最后一个非叶子节点
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustTopReactor(arr, i, arr.length);
        }

        //临时交换变量
        //不断的替换第一位和最后一位，因为每次调整之后最后一个值都是当前最大或者最小的
        //然后按照当前的位置再次调整为大顶堆
        for (int j = arr.length - 1, temp; j >= 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //再次调整大顶堆
            adjustTopReactor(arr, 0, j);
        }

    }

    /**
     * 重新生成大顶堆
     * @param arr 数组 ，表示二叉树
     * @param start  二叉树中跟节点的位置
     * @param length  数组中要调整的长度
     */
    public static void adjustTopReactor(int[] arr, int start, int length) {
        /*
         * 从最后
         * */
        int temp = arr[start];

        //int k = start * 2 + 1表示跟节点为start的位置的左子节点
        for (int k = start * 2 + 1; k < length; k = k * 2 + 1) {
            //选出左右子树最大的节点值对应的索引
            //如果转换为小顶堆，只需要在此处选择左右最小的
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果此时左右节点中有比较大的就放到父节点上
            //如果转换为小顶堆，只需要在此处temp与根节点哪个最小
            if (arr[k] > temp) {
                //比根节点要大
                arr[start] = arr[k];
                start = k;
            } else {
                break;
            }
        }
        //将刚才转换的temp值放到调整后的start位置
        arr[start] = temp;
    }
}
