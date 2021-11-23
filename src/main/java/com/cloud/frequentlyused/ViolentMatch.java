package com.cloud.frequentlyused;

/**
 * 暴力匹配算法
 *
 * @version v1.0
 * @ClassName ViolentMatch
 * @Author rayss
 * @Datetime 2021/6/6 7:03 下午
 */

public class ViolentMatch {

    public static void main(String[] args) {
        String parentString = "12123";
        String childString = "23";
        System.out.println(violentMatch(parentString, childString));
    }

    private static int violentMatch(String parentString, String childString) {
        char[] parentChars = parentString.toCharArray();
        char[] childChars = childString.toCharArray();

        int j = 0, i = 0;
        while (i < parentChars.length && j < childChars.length) {
            if (parentChars[i] == childChars[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == childChars.length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
