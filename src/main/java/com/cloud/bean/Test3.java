/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.bean;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <p>
 *最近，小七发现了一处干涸的河道。
 * 河道不同的位置高度也不相同，从河道起点到终点有n个位置，这些位置编号为1~n。每一个位置i的高度可以表示为hi(1、n是河道的两端，因此1左边、n右边的高度可以视为无穷大)。
 * 本着环保的精神，小七希望在恰好一个位置注入水源，使得这个位置是有水的。自然地，水会从高处向低处流动，但原来的位置仍然有水。具体地来说，如果当前一个位置i是有水的，
 * 并且有某一个相邻的格子j高度严格小于i(hj < hi)，那么j也会成为有水的，并且i仍然是有水的。对于j相邻的格子也是如此。
 * 现在小七想知道，通过一次注入水源最多可以使得多少个位置变成有水的
 * </p>
 * @author zhangyulei
 * @version :Test3.java v1.0 2021/9/25 3:25 下午 zhangyulei Exp $
 */
public class Test3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        int[] middle = new int[n];
        for (int i = 0; i < arr.length; i++) {
            int sum = 1;
            //计算左边
            for(int j = i; j > 0 ; j--){
                if(arr[j] < arr[j - 1]){
                    //此时说明水无法流过
                    break;
                }
                sum++;
            }
            //计算右边
            for(int j = i; j < arr.length - 1 ; j++){
                if(arr[j] < arr[j + 1]){
                    //此时说明水无法流过
                    break;
                }
                sum++;
            }
            middle[i] = sum;
        }
        int max = Arrays.stream(middle).max().getAsInt();
        System.out.println(max);
    }

}
