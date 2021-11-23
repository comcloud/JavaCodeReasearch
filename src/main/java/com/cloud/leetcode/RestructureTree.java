/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  根据前中序遍历结果重构二叉树
 * </p>
 * @author zhangyulei
 * @version :RestructureTree.java v1.0 2021/10/27 9:13 下午 zhangyulei Exp $
 */
public class RestructureTree {

    /**
     * 存储规则，节点数值：对应下标
     *  构造哈希映射，帮助我们快速定位根节点
     */
    private final Map<Integer, Integer> indexMap = new HashMap<>();

    public static void main(String[] args) {
        int[] pre = { 3, 9, 20, 15, 7 };
        int[] middle = { 9, 3, 15, 20, 7 };
        new RestructureTree().buildTree(pre, middle).postOrder();
    }

    public TreeNode myBuildTree(int[] preorder, int preorderLeft, int preorderRight, int inorderLeft) {
        if (preorderLeft > preorderRight) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        // 在中序遍历中定位根节点
        int inorderRoot = indexMap.get(preorder[preorderLeft]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorderLeft]);
        // 得到左子树中的节点数目，相当于下标相减
        int sizeLeftSubtree = inorderRoot - inorderLeft;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, preorderLeft + 1, preorderLeft + sizeLeftSubtree, inorderLeft);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, preorderLeft + sizeLeftSubtree + 1, preorderRight, inorderRoot + 1);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, n - 1, 0);
    }
}
