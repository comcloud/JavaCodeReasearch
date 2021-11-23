package com.cloud.leetcode;


import sun.misc.Unsafe;

/**
 * 判断两个树是否相同🌲
 * @version v1.0
 * @ClassName SampleTree
 * @Author rayss
 * @Datetime 2021/6/27 9:25 上午
 */

public class SampleTree {

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.setLeft(new TreeNode(1));
        TreeNode q = new TreeNode(1);
        q.setLeft(new TreeNode(1));
        System.out.println(isSameTree(p, q));
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null) {
            if (q != null) {
                if (p.value != q.value) {
                    return false;
                }
                boolean leftRes = isSameTree(p.left, q.left);
                boolean rightRes = isSameTree(p.right, q.right);
                return leftRes && rightRes;
            }
            return false;
        } else {
            return q == null;
        }
    }
}
