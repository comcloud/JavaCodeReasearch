/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Rotate.java v1.0 2021/11/20 3:16 下午 zhangyulei Exp $
 */
public class Rotate {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        new Rotate().rotate(matrix);

    }

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        System.out.println("\t");

        rot(matrix, 0, matrix.length);

        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    public void rotate(int[][] matrix, int startValue, int expectValue, int currentValue, int currentX, int currentY) {
        if (expectValue == startValue) {
            return;
        }
        int ctl = matrix[currentY][matrix.length - 1 - currentX];
        matrix[currentY][matrix.length - 1 - currentX] = currentValue;
        rotate(matrix, startValue,
                matrix[matrix.length - 1 - currentX][matrix.length - 1 - currentX - (matrix.length - 1 - currentX)],
                ctl, currentY, matrix.length - 1 - currentX);
    }

    public void rot(int[][] matrix, int level, int rank) {
        if (rank > 1) {
            for (int i = 0; i < rank - 1; ++i) {
                int temp = matrix[level][level + i];
                matrix[level][level + i] = matrix[level + rank - 1 - i][level];
                matrix[level + rank - 1 - i][level] = matrix[level + rank - 1][level + rank - 1 - i];
                matrix[level + rank - 1][level + rank - 1 - i] = matrix[level + i][level + rank - 1];
                matrix[level + i][level + rank - 1] = temp;
            }
            rot(matrix, level + 1, rank - 2);
        }
    }

}
