/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.frequentlyused;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * 骑士周游问题
 * </p>
 * @author zhangyulei
 * @version :HorseChessBoard.java v1.0 2021/11/7 3:35 下午 zhangyulei Exp $
 */
public class HorseChessBoard {

    private static final int X = 6;
    private static final int Y = 6;

    /**
     * 表示各个位置是否被访问过
     * */
    private static boolean[] visited = new boolean[X * Y];

    /**
     * 如果为true表示成功
     */
    private static boolean finished;

    public static void main(String[] args) {
        int[][] chessBoard = new int[X][Y];
        int row = 4;
        int column = 3;
        int step = 1;
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, row, column, step);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        for (int[] rows : chessBoard) {
            for (int s : rows) {
                System.out.print(s + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 马踏棋盘问题
     * @param chessBoard 棋盘
     * @param row 行
     * @param column 列
     * @param step 步数
     */
    public static void traversalChessBoard(int[][] chessBoard, int row, int column, int step) {
        chessBoard[row][column] = step;

        //因为下标从0开始，所以直接使用row*X表示就是当前位置的上一行所有个数，加上column就是第多少个，正好可以对应下标
        visited[row * X + column] = true;
        //获取当前位置可以走的所有位置集合
        ArrayList<Point> ps = next(new Point(column, row));
        //优化
        sort(ps);
        //遍历所有位置进行判断
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            //判断当前点是否已经走过
            if (!visited[p.y * X + p.x]) {
                traversalChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }

        //此时可能还没有走完
        //第一种表示走的步数已经是棋盘的个数与完成
        if (step < X * Y && !finished) {
            //此时没有完成
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            //说明成立
            finished = true;
        }
    }

    public static ArrayList<Point> next(Point currentPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();

        //对所有位置进行判断，判断周围可以走的位置，如果可以走就添加到ps中记录下来
        if ((p1.x = currentPoint.x - 2) >= 0 && (p1.y = currentPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x - 1) >= 0 && (p1.y = currentPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x + 1) < X && (p1.y = currentPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x + 2) < X && (p1.y = currentPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x + 2) < X && (p1.y = currentPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x + 1) < X && (p1.y = currentPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x - 1) >= 0 && (p1.y = currentPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = currentPoint.x - 2) >= 0 && (p1.y = currentPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }

        return ps;
    }

    /**
     * 骑士周游为题优化，优化思路是每次选择下次走的路线的时候，先尝试走最少的
     * 所以这里对所有下次走的路线优化思路就是让next列表变为非递减列表
     * @param next
     */
    public static void sort(ArrayList<Point> next){
        next.sort(Comparator.comparingInt(o -> next(o).size()));
    }

}
