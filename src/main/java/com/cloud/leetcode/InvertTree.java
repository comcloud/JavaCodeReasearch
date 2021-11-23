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
 * @version :InvertTree.java v1.0 2021/11/13 9:42 上午 zhangyulei Exp $
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }
}
