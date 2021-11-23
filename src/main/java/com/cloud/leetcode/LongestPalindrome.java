/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *给你一个字符串 s，找到 s 中最长的回文子串。
 * </p>
 * @author zhangyulei
 * @version :LongestPalindrome.java v1.0 2021/10/25 4:37 下午 zhangyulei Exp $
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "aacabdkacaa";
        System.out.println(new LongestPalindrome().spread(s));
    }

    public String longestPalindromeTo(String s) {
        char[] cs = s.toCharArray();
        //resLen记录res的长度，len为字符串的长度
        int resLen = 0, len = s.length();
        //记录结果的起始位置
        int start = 0;
        //一共有2*len-1个中心，即有len + (len - 1)
        for (int i = 0; i < 2 * len - 1; i++) {
            //双指针初始化
            //当i为偶数时，left和right指向的是同一个字符，也就是单个字符为中心
            //当i为奇数时，left和right指向的是相邻的两个字符，也就是两个字符为中心
            int left = i / 2;
            int right = (i + 1) / 2;
            //向两边扩散，记得判断边界
            while (left >= 0 && right < len && cs[left] == cs[right]) {
                //如果当前的回文字符串比res的长度大，更新resLen，并且记录开始位置，即left
                if (right - left + 1 > resLen) {
                    start = left;
                    resLen = right - left + 1;
                }
                left--;
                right++;
            }
        }
        //返回结果，start开始，长度为reslen的字串
        return s.substring(start, start + resLen);
    }

    public String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return "";
        }
        Map<Integer, String> map = new HashMap<>();
        map.put(1, s.charAt(0) + "");
        for (int i = 0; i < s.length(); i++) {
            int end = s.lastIndexOf(s.charAt(i));
            String substring = s.substring(i, end + 1);
            while (i < end) {
                if (!isPalindrome(substring)) {
                    substring = substring.substring(0, substring.lastIndexOf(s.charAt(i)));
                    end = substring.lastIndexOf(s.charAt(i));
                } else {
                    map.put(substring.length(), substring);
                    break;
                }
            }
        }
        return map.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getKey)).get().getValue();
    }

    public boolean isPalindrome(String str) {
        int start = 0, end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start++) != str.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    /**扩散法*/
    public String spread(String s) {
        int length = 0, start = 0;

        for (int i = 0; i < s.length() * 2 - 1; i++) {
            //中间的点
            int left = i / 2;
            int right = (i + 1) / 2;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > length) {
                    length = right - left + 1;
                    start = left;
                }
                left--;
                right++;
            }
        }
        return s.substring(start, start + length);
    }

}
