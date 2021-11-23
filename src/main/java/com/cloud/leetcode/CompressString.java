package com.cloud.leetcode;

/**
 * @version v1.0
 * @ClassName CompressString
 * @Author rayss
 * @Datetime 2021/6/22 5:28 下午
 */

public class CompressString {
    public static void main(String[] args) {
        String s = "abbccddddddd";
        System.out.println(compressString(s));
    }

    public static String compressString(String s) {
        if("".equals(s)) return "";
        StringBuilder builder = new StringBuilder();
        char[] charArray = s.toCharArray();
        int count = 1;
        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i - 1] == charArray[i]) {
                count++;
            } else {
                builder.append(charArray[i - 1]).append(count);
                count = 1;
            }
        }
        builder.append(charArray[charArray.length - 1]).append(count);
        return builder.toString().length() > s.length() ? s : builder.toString();
    }
}
