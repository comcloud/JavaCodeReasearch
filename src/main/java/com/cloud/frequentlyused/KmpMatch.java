package com.cloud.frequentlyused;

/**
 * KMP算法
 *
 * @version v1.0
 * @ClassName KmpMatch
 * @Author rayss
 * @Datetime 2021/6/6 7:49 下午
 */

public class KmpMatch {

    public static void main(String[] args) {
        String childString = "ABCDABCABC";
        String parentString = "123ABCDABCABC123";
        System.out.println(parentString.indexOf(childString));
        System.out.println(search(parentString, childString));
    }

    private static int search(String parentString, String childString) {
        int[] next = getNext(childString);
        char[] chars = parentString.toCharArray();
        for (int i = 0, j = 0; i < chars.length; i++) {
            while (j > 0 && chars[i] != childString.charAt(j)) {
                //让j移动到已经匹配到的位置，这里需要注意为什么使用循环，是因为需要不断的进行判断，直到匹配到想等位置重新判断
                j = next[j - 1];
            }
            if (chars[i] == childString.charAt(j)) {
                j++;
            }
            if (j == childString.length()) {
                //此时需要进行+1因为，还未对i++
                return i - j + 1;
            }
        }
        return -1;
    }

    private static int[] getNext(String string) {
        int[] next = new int[string == null ? 0 : string.length()];
        if (string == null || string.length() == 0) {
            return next;
        }
        for (int i = 1, j = 0; i < string.length(); i++) {
            while (j > 0 && string.charAt(i) != string.charAt(j)) {
                //此时相当于回溯，不断的将j回溯到上一个，回溯到上个想等的位置即可，当然要保证j>0
//                j = next[j - 1];
                j--;
            }

            if (string.charAt(i) == string.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
