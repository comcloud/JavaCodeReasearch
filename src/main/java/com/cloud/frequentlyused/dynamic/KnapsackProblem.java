package com.cloud.frequentlyused.dynamic;

/**
 * @version v1.0
 * @ClassName KnapsackProblem
 * @Author rayss
 * @Datetime 2021/6/6 6:10 下午
 */

public class KnapsackProblem {
    public static void main(String[] args) {
        //定一个物品的重量
        int[] w = {1, 4, 3};
        //物品的价值
        int[] val = {1500, 3000, 2000};
        //背包的容量
        final int m = 4;
        //物品的个数
        int n = val.length;

        //二维数组，存储的为最大价值，i对应前i个物品，j表示背包的容量,+1表示记录0时候的情况
        int[][] v = new int[n + 1][m + 1];
        //记录最优解位置
        int[][] path = new int[n + 1][m + 1];

        //动态规划处理二维数组，因为处理为第一行和第一列都是0，所以都是从1开始
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                //因为i从1开始，所以单独获取重量时候需要对下标进行-1操作获取到正确的值
                if (w[i - 1] > j) {
                    //如果此时当前重量大于背包重量，则直接使用上一个重量
                    v[i][j] = v[i - 1][j];
                } else {
                    //此时也相同，部分需要进行-1操作
                    //获取上一个的最大价值跟此时计算的最大价值相比取最大，对于计算 当前最大价值 = 此时放入的物品的质量+背包重量剩下重量可以存放的内容
                    //对于背包剩下重量可以存放的内容:v[i-1][j-w[i-1]]，首先是上一行(之所以上一行是因为在上一行时候计算出来的已经是最大价值)，然后获取到对应背包重量的价值即可
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //打印二维数组
        for (int[] intS : v) {
            for (int anInt : intS) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

        for (int i = path.length - 1, j = path[0].length - 1; i >= 0 && j >= 0; i--) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个内容放入背包");
                //移动j位置，上面已经打印出来存放的一个内容，还有个内容就是装下上面内容之后剩下的背包容量，当然这里需要进行-1操作
                j -= w[i - 1];
            }

        }
    }
}
