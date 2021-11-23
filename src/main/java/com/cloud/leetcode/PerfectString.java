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
 * @version :PerfetString.java v1.0 2021/9/20 2:41 下午 zhangyulei Exp $
 */
public class PerfectString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int m = scanner.nextInt();
        System.out.println(getPerfectStringLength(str, m));

    }

    public static int getPerfectStringLength(String str,int m){
        int ans = 0;
        //遍历26次，是因为要走26个字母判断
        for (int i = 0; i < 26; i++) {
            int left = 0, used = 0;
            for(int right = 0; right < str.length(); right++){
                if(str.charAt(right) != 'a' + i){
                    used++;
                }
                //如果此时使用次数已经超过了
                while(used > m){
                    //此时移动左指针，移动多少个其实也就是可以弥补多少个，弥补意思就是说使用次数就是目标字母序列，那么移动一个目标字母序列中字母类型才会让used--
                    //这样才会平衡used == m
                    if(str.charAt(left++) != 'a' + i){
                        used--;
                    }
                }
                ans = Math.max(ans, right - left + 1);
            }
        }
        return ans;
    }
}
