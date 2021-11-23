package com.cloud.leetcode;

import com.cloud.tree.AvlTreeDemo;
import com.cloud.tree.BinarySortTreeDemo;

import java.util.TreeMap;

/**
 * 计算二叉树最小深度
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 * 输入的root均为广度优先遍历
 *
 * @version v1.0
 * @ClassName MinDeepOfTree
 * @Author rayss
 * @Datetime 2021/6/5 4:45 下午
 */

public class MinDeepOfTree {

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6};
//        int[] arr = {3, 2, 20, 15, 21};
        BinarySortTreeDemo.BinarySortTree tree = new BinarySortTreeDemo.BinarySortTree(arr);
        BinarySortTreeDemo.Node root = tree.getRoot();
        int minHeight = minDepth(root);
        System.out.println(minHeight);
    }

    public static int minDepth(BinarySortTreeDemo.Node root) {
        if (root == null) {
            return 0;
        }
        // null节点不参与比较
        if (root.getLeft() == null && root.getRight() != null) {
            return 1 + minDepth(root.getRight());
        }
        // null节点不参与比较
        if (root.getRight() == null && root.getLeft() != null) {
            return 1 + minDepth(root.getLeft());
        }
        return 1 + Math.min(minDepth(root.getLeft()), minDepth(root.getRight()));
    }

}
