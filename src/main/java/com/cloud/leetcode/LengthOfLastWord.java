package com.cloud.leetcode;

/**
 * @version v1.0
 * @ClassName LengthOfLastWord
 * @Author rayss
 * @Datetime 2021/6/24 2:12 下午
 */

public class LengthOfLastWord {
    public static void main(String[] args) {
        String s = "Hello world";
        System.out.println(lengthOfLastWord(s));
    }

    public static int lengthOfLastWord(String s) {
        s = s.trim();
        if(!s.contains(" ")) {
            return s.length();
        }
        return s.substring(s.lastIndexOf(" ") + 1).length();
    }
}
