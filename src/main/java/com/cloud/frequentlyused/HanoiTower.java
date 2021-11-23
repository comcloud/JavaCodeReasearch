package com.cloud.frequentlyused;

/**
 * 汉诺塔问题，使用分治思想
 *
 * @version v1.0
 * @ClassName HanoiTower
 * @Author rayss
 * @Datetime 2021/6/5 11:21 下午
 */

public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(10,'A','B','C');
    }

    /**
     * 移动汉诺塔方法
     *
     * @param num 当前汉诺塔的数量
     * @param a   a盘
     * @param b   b盘
     * @param c   c盘
     */
    private static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println(a + " -> " + c);
        } else {
            //先将上面的所有塔都移动到b位置
            hanoiTower(num - 1, a, c, b);
            //移动剩下的一个
            System.out.println(a + " -> " + c);
            //把刚才移动到b位置的塔都移动到c
            hanoiTower(num - 1, b, a, c);
        }
    }
}
