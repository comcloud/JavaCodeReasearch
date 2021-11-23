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
 * @version :IsBanalced.java v1.0 2021/11/22 5:23 下午 zhangyulei Exp $
 */
public class IsBalanced {

    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //记录左子树的高度以及右子树的高度
        int lh = height(root.left), rh = height(root.right);

        if (lh >= 0 && rh >= 0 && Math.abs(lh - rh) <= 1) {
            return Math.max(lh, rh) + 1;
        } else {
            return -1;
        }
    }

}
