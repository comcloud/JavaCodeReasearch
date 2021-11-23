package com.cloud.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author 成都犀牛
 * @version 1.0
 * @datetime 2021/3/19 12:10 下午
 */
@SuppressWarnings("all")
public class MainController {
    public static void main(String[] args) {
        int[] arr = {23, 4, 23, 5, 42, 35, 34534, 5};
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
        int part = partition(arr, left, right);

        quickSort(arr, left, part - 1);
        quickSort(arr, part + 1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        int pos = arr[left];
        while (left < right) {
            //找到右边比基准小的
            while (left < right && arr[right] >= pos) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pos) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pos;
        return left;
    }


    private static int result(int tab) {
        int n = tab - 1;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        return n;
    }

    private static String getResult() {
        CompletableFuture[] completableFutures = new CompletableFuture[10];
        for (int i = 0; i < 10; i++) {
            completableFutures[i] = CompletableFuture.runAsync(() -> isOddNumber(20));
        }
        try {
            CompletableFuture.allOf(completableFutures).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "执行成功";
    }

    //判断是否为奇数
    private static boolean isOddNumber(int count) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i;
        for (i = 2; i < count; i++) {
            System.out.println(Thread.currentThread().getName() + " :: " + i);
            if (count % i == 0) {
                break;
            }
        }
        return i == count;
    }

    public static Integer hashCode(String str, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i);
        }
        return hash;
    }

    /**
     * 计算 hash code 冲突率，顺便分析一下 hash code 最大值和最小值，并输出
     */
    public static void calculateConflictRate(Integer multiplier, List<Integer> hashs) {
        Comparator<Integer> cp = Integer::compareTo;

        int maxHash = hashs.stream().max(cp).get();
        int minHash = hashs.stream().min(cp).get();

        // 计算排除冲突的总共数量
        int uniqueHashNum = (int) hashs.stream().distinct().count();
        //计算出有几个冲突
        int conflictNum = hashs.size() - uniqueHashNum;

        double conflictRate = (conflictNum * 1.0) / hashs.size();
        System.out.printf("multiplier=%4d, minHash=%11d, maxHash=%10d, conflictNum=%6d, conflictRate=%.4f%%%n"
                , multiplier, minHash, maxHash, conflictNum, conflictRate * 100);

    }


}
