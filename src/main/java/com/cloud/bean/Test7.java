/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.bean;

import com.cloud.leetcode.TreeNode;

import java.util.Objects;
import java.util.Stack;

/**
 * <p>
 *
 * </p>
 * @author zhangyulei
 * @version :Test7.java v1.0 2021/9/26 8:41 下午 zhangyulei Exp $
 */
public class Test7 {

    public static void main(String[] args) {

    }

    public TreeNode solve(TreeNode root, int[][] b) {
        // write code here
        for (int i = 0; i < b.length; i++) {
            //不是祖先后代关系
            if (!isFa(root, b[i][0], b[i][1])) {
                TreeNode fa1 = getFa(root, b[i][0]);
                TreeNode fa2 = getFa(root, b[i][0]);
                TreeNode node1 = getNode(root, b[i][0]);
                TreeNode node2 = getNode(root, b[i][1]);
                if (fa1.getValue() > b[i][0]) {
                    //说明是左子树
                    fa1.setLeft(node2);
                } else {
                    fa1.setRight(node2);
                }
                if (fa2.getValue() > b[i][1]) {
                    fa2.setLeft(node1);
                } else {
                    fa2.setRight(node1);
                }
            }
        }
        return null;
    }

    public TreeNode getFa(TreeNode root, int value) {
        if (Objects.isNull(root)) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        do {
            TreeNode pop = stack.pop();
            if (pop.getRight() != null) {
                if (pop.getRight().getValue() == value) {
                    return pop;
                }
                stack.push(pop.getRight());
            }
            if (pop.getLeft() != null) {
                if (pop.getLeft().getValue() == value) {
                    return pop;
                }
                stack.push(pop.getLeft());
            }
        } while (!stack.isEmpty());
        return null;
    }

    public boolean isFa(TreeNode root, int val1, int val2) {
        TreeNode head;
        TreeNode next;
        if (val1 < val2) {
            head = getNode(root, val1);
            next = getNode(root, val2);
        } else {
            head = getNode(root, val2);
            next = getNode(root, val1);
        }
        return exist(head, next);
    }

    public boolean exist(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1 == node2) {
            return true;
        }
        if (node1.getLeft() != null) {
            return exist(node1.getLeft(), node2);
        }
        if (node1.getRight() != null) {
            return exist(node1.getRight(), node2);
        }
        return false;
    }

    public TreeNode getNode(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        if (root.getValue() == value) {
            return root;
        }
        if (root.getLeft() != null) {
            return getNode(root.getLeft(), value);
        }
        if (root.getRight() != null) {
            return getNode(root.getRight(), value);
        }
        return null;
    }
}
