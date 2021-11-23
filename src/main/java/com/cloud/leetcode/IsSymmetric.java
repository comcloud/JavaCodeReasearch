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
 * @version :IsSymmetric.java v1.0 2021/11/12 9:05 下午 zhangyulei Exp $
 */
public class IsSymmetric {

    public boolean isSymmetric(TreeNode root){
        return check(root, root);
    }

    public boolean check(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }
        if(left == null || right == null){
            return false;
        }
        //值相等，left左移时，right右移；left右移时，right左移，这样可以覆盖全部
        return left.value == right.value && check(left.left, right.right) && check(right.left, left.right);
    }
}
