package com.cloud.MainTest.datastructure;

/**
 * 稀疏数组
 *
 * @version v1.0
 * @ClassName SparseArray
 * @Author rayss
 * @Datetime 2021/5/8 3:41 下午
 */

public class SparseArray {
    public static void main(String[] args) {
        int[][] chess = new int[11][11];
        chess[1][2] = 1;
        chess[2][3] = 2;
        chess[4][5] = 1;
        chess[3][5] = 1;
        System.out.println("non sparse array");
        for (int[] ints : chess) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

        System.out.println("sparse array:");
        int[][] sparseArray = twoDimensionalArrayToSparseArray(chess);
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

        System.out.println("change again");
        int[][] twoDimensionalArray = sparseArrayToTwoDimensionalArray(sparseArray);
        for (int[] ints : twoDimensionalArray) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    private static int[][] twoDimensionalArrayToSparseArray(int[][] chessArray) {
        int sum = 0;
        for (int[] ints : chessArray) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = sum;
        int j = 1;
        for (int i = 0; i < chessArray.length; i++) {
            for (int i1 = 0; i1 < chessArray[i].length; i1++) {
                if (chessArray[i][i1] != 0) {
                    sparseArray[j][0] = i;
                    sparseArray[j][1] = i1;
                    sparseArray[j][2] = chessArray[i][i1];
                    j++;
                }
            }
        }
        return sparseArray;
    }

    private static int[][] sparseArrayToTwoDimensionalArray(int[][] sparseArray){
        int[][] array = new int[sparseArray[0][0]][sparseArray[0][1]];

        for (int i = 1; i < sparseArray.length; i++) {
            array[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return array;
    }
}
