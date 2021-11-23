/**
 * meituan.com Inc.
 * Copyright (c) 2010-2021 All Rights Reserved.
 */
package com.cloud.leetcode;

/**
 * <p>
 *  所有左叶子之和
 * </p>
 * @author zhangyulei
 * @version :SumOfLeftLeaves.java v1.0 2021/11/13 10:07 上午 zhangyulei Exp $
 */
public class SumOfLeftLeaves {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode rootLeft = new TreeNode(3);
        TreeNode rootLeftRight = new TreeNode(-1);
        rootLeft.setRight(rootLeftRight);

        TreeNode rootLeftLeft = new TreeNode(1);
        TreeNode rootRight = new TreeNode(7);
        TreeNode rootRightLeft = new TreeNode(6);
        TreeNode rootRightRight = new TreeNode(8);
        root.setLeft(rootLeft);
        rootLeft.setLeft(rootLeftLeft);
        root.setRight(rootRight);
        rootRight.setRight(rootRightRight);
        rootRight.setLeft(rootRightLeft);
        System.out.println(new SumOfLeftLeaves().sumOfLeftLeaves(root));
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int l = 0;
        if(root.left != null && root.left.left == null && root.left.right == null){
            l = root.left.value;
        }
        return l + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
}
