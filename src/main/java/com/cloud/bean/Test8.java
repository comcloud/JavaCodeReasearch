/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.bean;

import java.util.Scanner;

/**
 * <p>
 *3
 * </p>
 * @author zhangyulei
 * @version :Test8.java v1.0 2021/9/26 9:13 下午 zhangyulei Exp $
 */
public class Test8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = scanner.nextLine();
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            builder.append(str[i]);
        }
        if(!builder.toString().contains("#")){
            System.out.println("0 0");
        }
    }
}
