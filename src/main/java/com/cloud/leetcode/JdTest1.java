/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :JdTest1.java v1.0 2021/10/9 7:39 下午 zhangyulei Exp $
 */
public class JdTest1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        //找到出现次数最多的那个
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            map.merge(j, 1, Integer::sum);
        }
        Integer key = map.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
        //第一个位置与最后一个位置
        int first = 0, second = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key && first == 0) {
                first = i + 1;
            }
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == key && second == 0) {
                second = i + 1;
            }
        }
        System.out.println(first + "\t" + second);
    }
}
