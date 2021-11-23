/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :MeiTuanTest1.java v1.0 2021/10/10 10:08 上午 zhangyulei Exp $
 */
public class MeiTest1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        int[][] need = new int[n][3];
        int[][] inFact = new int[m][3];

        for (int i = 0; i < need.length; i++) {
            for (int j = 0; j < need[i].length; j++) {
                need[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < need.length; i++) {
            for (int j = 0; j < need[i].length; j++) {
                inFact[i][j] = scanner.nextInt();
            }
        }        //错误次数
        int errorSum = 0;
        for (int i = 0; i < need.length; i++) {
            //这里分四种情况
            /*
            * 1.在范围区间
            * 2.超出左边区间
            * 3.超出右边区间
            * 4.两边都超过
            * */
            if(inFact[i][0] >= need[i][0] && inFact[i][1] <= need[i][1]){
                //此时判断是否是需要的动作
                if(inFact[i][2] != need[i][2] && (inFact[i][1] - inFact[i][0]) < k){
                    errorSum++;
                }
            }else if(inFact[i][0] <= need[i][0] && inFact[i][1] <= need[i][1] && (inFact[i][1] - inFact[i][0]) < k){
                //右边比左边小
                errorSum++;
            }else if(inFact[i][0] >= need[i][0] && inFact[i][1] >= need[i][1] && (inFact[i][1] - inFact[i][0]) < k){
                //左边比右边大
                errorSum++;
            }else{
                //两边都超
                if(inFact[i][2] != need[i][2]){
                    errorSum++;
                }else {
                    errorSum += 2;
                }
            }

        }
        System.out.println(errorSum);
    }
}
