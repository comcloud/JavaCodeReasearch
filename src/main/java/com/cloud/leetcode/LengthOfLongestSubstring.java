package com.cloud.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @version v1.0
 * @ClassName LengthOfLongestSubstring
 * @Author rayss
 * @Datetime 2021/8/16 5:33 下午
 */

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring2(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        Map<Character, Object> map = new HashMap<>(10);
        int initIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == null) {
                if(map.isEmpty()){
                    //基础首次为空的位置，在下次轮询判断时候的起点
                    initIndex = i;
                }
                map.put(s.charAt(i), new Object());
            } else {
                int size = map.size();
                map.clear();
                i = initIndex;
                maxLength = Math.max(maxLength, size);
            }
        }
        return Math.max(maxLength, map.size());
    }

    public static int lengthOfLongestSubstring2(String s){
        int minIndex = 0;
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.indexOf(s.charAt(i), minIndex) < i) {
                minIndex = s.indexOf(s.charAt(i), minIndex) + 1;
            } else {
                ans = Math.max(ans, i - minIndex + 1);
            }
        }
        return ans;
    }
}
