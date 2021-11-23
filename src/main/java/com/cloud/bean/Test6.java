/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.bean;

import java.util.Scanner;

/**
 * <p>
 * 表达式计算
 * </p>
 * @author zhangyulei
 * @version :Test6.java v1.0 2021/9/26 8:12 下午 zhangyulei Exp $
 */
public class Test6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split("\\+");
        long result = 0L;
        for (String s : split) {
            //10  2@1x2
            //10  2@1 2
            String[] split1 = s.split("x");
            long temp = 1;
            for (String s1 : split1) {
                if(s1.contains("@")){
                    String[] split2 = s1.split("@");
                    long middle = split2.length >=2 ?  0L : Long.parseLong(split2[0] == null ? "" : split2[0]);
                    for (int i = 1; i < split2.length; i++) {
                        middle += Long.parseLong(split2[i - 1]) | (Long.parseLong(split2[i - 1]) + Long.parseLong(split2[i]));
                    }
                    temp *= middle;
                }else{
                    temp *= Long.parseLong(s1);
                }
            }
            result += temp;
        }
        System.out.println(result);
    }
}
